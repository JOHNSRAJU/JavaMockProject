package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gui.AcceptPanel;
import gui.AddPatientListener;
import gui.AddPatientFrame;
import gui.FormPanel;
import gui.MainFrame;
import gui.PatientTableModel;
import gui.ScanningWindow;
import model.Database;
import model.Patient;
import model.Status;
public class Controller {
	private Database db;
	static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.registerModule(new JavaTimeModule());
	}
	private static final String FILEPATH = "src\\main\\resources\\patients.json";
	public Controller() {
		db = new Database();
	}
	public Database getDb() {
		return db;
	}


	public static ArrayList<Patient> readJsonFromFile() {

		try {
			return objectMapper.readValue(new File(FILEPATH), new TypeReference<ArrayList<Patient>>() {});
		} catch (IOException e) {
			System.err.println("Error reading JSON from file: " + e.getMessage());
		}
		return null;
	}
	public void addPatientToList(Patient patient) {
		db.getPatients().add(patient);
	}
	public static void writeJsonToFile(List<Patient> array) {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILEPATH), array);

		} catch (IOException e) {
			System.err.println("Error writing JSON to file: " + e.getMessage());
		}
	}

	public static double calculateBMI(double weight,double height) {
		if(height<0) {
			throw new IllegalArgumentException("Height should greater than Zero");
		}
		else if(weight<0) {
			throw new IllegalArgumentException("Weight should greater than Zero");
		}
		return Double.parseDouble(String.format("%.2f",(weight*10000)/(height*height)));
	}

	public Patient createPatientObject(String id,String name,Date dob, String weight,String height, String description,JFrame frame) {
		if(id!=null&&name!=null&&dob!=null&&weight!=null&&height!=null) {
			Patient patient = new Patient(Integer.parseInt(id), name, dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), Double.parseDouble(weight), Double.parseDouble(height), description);
			System.out.println(patient);
			return patient;
		}
		return null;
	}

	public void refreshTable(PatientTableModel patientTableModel) {
		patientTableModel.fireTableDataChanged();
	}
	public void addAcceptPanel(MainFrame frame,AcceptPanel acceptPanel) {
		frame.add(acceptPanel,BorderLayout.SOUTH);
		frame.revalidate();
	}
	public void addUserAndWriteToJson(MainFrame mainFrame, AddPatientFrame addUserFrame,PatientTableModel patientTableModel) {
		if(validateForm(addUserFrame.getFormPanel(), addUserFrame)) {
			Patient patient = createPatientObject(addUserFrame.getFormPanel().getId().getText(), addUserFrame.getFormPanel().getPatientName().getText(),addUserFrame.getFormPanel().getDateChooser().getDate(), addUserFrame.getFormPanel().getWeight().getText(), addUserFrame.getFormPanel().getPatientHeight().getText(),addUserFrame.getFormPanel().getDescriptionArea().getText(),addUserFrame);
			if(patient!=null) {
				addPatientToList(patient);
				writeJsonToFile(getDb().getPatients());
				refreshTable(patientTableModel);
				addUserFrame.dispose();
			}
		}
	}
	public void addMouseAndActionListener(JButton addPatientButton,AddPatientListener addPatientListener) {
		addPatientButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				addPatientButton.setBackground(new Color(50, 205, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addPatientButton.setBackground(new Color(34, 139, 34));
			}

		});
		addPatientButton.addActionListener((event)->{
			if(event.getSource()==addPatientButton) {
				addPatientListener.addPatientClicked();
			}
		});
	}
	public Object getValueForTableModel(int rowIndex,int columnIndex) {
		if (db.getPatients().size()>0) {
			Patient patient = db.getPatients().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return rowIndex+1;
			case 1:
				return patient.getId();
			case 2:
				return patient.getName();
			case 3:
				return LocalDate.now().getYear()-patient.getDob().getYear();
			case 4:
				return patient.getWeight();
			case 5:
				return patient.getHeight();
			case 6:
				return patient.getBmi();
			case 7:
				return patient.getDescription();
			case 8:
				return patient.getStatus();
			}
		}
		return null;
	}

	private boolean validateForm(FormPanel formPanel, JFrame frame) {

		try {
			int id = Integer.parseInt(formPanel.getId().getText());
			if (id <= 0) {
				showError("ID must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("ID must be a valid number.",frame);
			return false;
		}
		if (formPanel.getPatientName().getText().isEmpty()) {
			showError("Name is required.",frame);
			return false;
		}

		if(formPanel.getDateChooser().getDate() == null){
			showError("Date of Birth is required", frame);
			return false;
		}
		LocalDate dob = formPanel.getDateChooser().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (dob == null) {
			showError("Invalid Date",frame);
			return false;
		}
		else if((LocalDate.now().compareTo(dob))==-1) {
			showError("Invalid Date",frame);
			return false;
		}
		try {
			double height = Double.parseDouble(formPanel.getPatientHeight().getText());
			if (height <= 0) {
				showError("Height must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("Height must be a valid number.",frame);
			return false;
		}

		try {
			double weight = Double.parseDouble(formPanel.getWeight().getText());
			if (weight <= 0) {
				showError("Weight must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("Weight must be a valid number.",frame);
			return false;
		}
		if (formPanel.getDescriptionArea().getText().length()>200) {
			showError("Description should below 200 characters.",frame);
			return false;
		}

		return true; // If all checks passed
	}
	private void showError(String message, JFrame frame) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.OK_OPTION|JOptionPane.ERROR_MESSAGE);	    
	}

	public static void addExitButtonListener(JButton exitButton,JProgressBar progressBar, ScanningWindow window, MainFrame mainFrame) {
		exitButton.addActionListener((e)->{
			if (progressBar.getValue() < 100) {
				int response = JOptionPane.showConfirmDialog(
						window, 
						"The scan is not complete. Are you sure you want to exit?", 
						"Confirm Exit", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE
						);
				if (response == JOptionPane.NO_OPTION) {
					window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else {
					mainFrame.setEnabled(true);
					window.dispose();
				}
			} else {
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
	}
	public static void addCompleteButtonListener(JButton completeButton,ScanningWindow window, MainFrame mainFrame,Patient patient,PatientTableModel tableModel) {
		completeButton.addActionListener((event)->{
			ArrayList<Patient> patients = readJsonFromFile();
			for(Patient tempPatient : patients) {
				if(patient.equals(tempPatient)) {
					tempPatient.setStatus(Status.COMPLETED);
				}
			}
			patient.setStatus(Status.COMPLETED);
			writeJsonToFile(patients);
			mainFrame.setEnabled(true);
			tableModel.fireTableDataChanged();
			window.dispose();
		});
	}
	public static ArrayList<Patient> readJsonFile(File file) {
		try {
			return objectMapper.readValue(file, new TypeReference<ArrayList<Patient>>() {});
		} catch (IOException e) {
			System.err.println("Error reading JSON from file: " + e.getMessage());
		}
		return null;
	}
	
	public void importData(PatientTableModel patientTableModel) {
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setDialogTitle("Import");
		int returnValue = fileChooser.showOpenDialog(fileChooser);
		
		if (returnValue==JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ArrayList<Patient> importedPatients = Controller.readJsonFile(selectedFile);
			if(importedPatients!=null) {
				getDb().getPatients().addAll(importedPatients);
				refreshTable(patientTableModel);
				writeJsonToFile(getDb().getPatients());
				JOptionPane.showMessageDialog(fileChooser, "Patient details successfully imported");
			}else {
				JOptionPane.showMessageDialog(fileChooser, "error");
			}
			
		}
	}
	
	public static void writeJsonToFile(List<Patient> array, File file) {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, array);

		} catch (IOException e) {
			System.err.println("Error writing JSON to file: " + e.getMessage());
		}
	}
	
	public void exportData(PatientTableModel patientTableModel) {
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setDialogTitle("Export");
		int returnValue = fileChooser.showSaveDialog(fileChooser);
		if (returnValue==JFileChooser.APPROVE_OPTION) {
			File saveFile = fileChooser.getSelectedFile();
			if(db.getPatients()!=null||db.getPatients().size()>0) {
				writeJsonToFile(db.getPatients(),saveFile);
				JOptionPane.showMessageDialog(fileChooser, "Patient details successfully");
			}
			else {
				JOptionPane.showMessageDialog(fileChooser, "Empty Data");
			}
		}
	}
	public void openData(PatientTableModel patientTableModel, MainFrame frame) {
		if(JOptionPane.showConfirmDialog(frame,"Save","Do you want save current data?",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
			exportData(patientTableModel);
		}
		db.getPatients().removeAll(db.getPatients());
		importData(patientTableModel);
	}
	public void newFile(PatientTableModel patientTableModel, MainFrame frame) {
		if(JOptionPane.showConfirmDialog(frame,"Save","Do you want save current data?",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
			exportData(patientTableModel);
		}
		db.getPatients().removeAll(db.getPatients());
		refreshTable(patientTableModel);
	}
}
