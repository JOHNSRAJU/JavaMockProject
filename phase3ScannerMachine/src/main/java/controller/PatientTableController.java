package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gui.AcceptEditPanel;
import gui.AddPatientListener;
import gui.MainFrame;
import model.Database;
import model.Patient;
import model.PatientTableModel;

public class PatientTableController {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static JFileChooser fileChooser=new JFileChooser();
	private static final String INTERNALFILEPATH = "src\\main\\resources\\patients.json";
	private Database database = Controller.getDatabase();
	private PatientTableModel tableModel;
	static {
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	public static ArrayList<Patient> readJsonFromFile() {
		try {
			return objectMapper.readValue(new File(INTERNALFILEPATH), new TypeReference<ArrayList<Patient>>() {});
		} catch (IOException e) {
			System.err.println("Error reading JSON from file: " + e.getMessage());
		}
		return null;
	}
	public PatientTableController(PatientTableModel tableModel) {
		super();
		this.tableModel = tableModel;
	}

	public void addAcceptPanel(MainFrame frame,AcceptEditPanel acceptPanel) {
		frame.add(acceptPanel,BorderLayout.SOUTH);
		frame.revalidate();
	}
	
	public Object getValueForTableModel(int rowIndex,int columnIndex, PatientTableModel patientTableModel) {
		if (patientTableModel.getFilteredData().size()>0) {
			Patient patient = patientTableModel.getData(rowIndex);
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
	
	public void importData(PatientTableModel patientTableModel) {
		fileChooser.setDialogTitle("Import");
		int returnValue = fileChooser.showOpenDialog(fileChooser);

		if (returnValue==JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			
			if(!FileValidationController.isValidJsonFile(selectedFile)) {
				JOptionPane.showMessageDialog(fileChooser, "Please select a valid json file","Invalid File",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ArrayList<Patient> importedPatients = readJsonFile(selectedFile);
			if(importedPatients!=null) {
				database.getPatients().addAll(importedPatients);
				Controller.writeJsonToFile(database.getPatients());
				Controller.refreshTable(patientTableModel);
				JOptionPane.showMessageDialog(fileChooser, "Patient details successfully imported");
			}else {
				JOptionPane.showMessageDialog(fileChooser, "error");
			}

		}
	}
	public ArrayList<Patient> readJsonFile(File file) {
		try {
			return objectMapper.readValue(file, new TypeReference<ArrayList<Patient>>() {});
		} catch (IOException e) {
			System.err.println("Error reading JSON from file: " + e.getMessage());
		}
		return null;
	}

	public static void writeJsonToFile(ArrayList<Patient> array, File file) {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, array);

		} catch (IOException e) {
			System.err.println("Error writing JSON to file: " + e.getMessage());
		}
	}

	public void exportData(PatientTableModel patientTableModel) {
		fileChooser.setDialogTitle("Export");
		int returnValue = fileChooser.showSaveDialog(fileChooser);
		if (returnValue==JFileChooser.APPROVE_OPTION) {
			File saveFile = fileChooser.getSelectedFile();
			
			saveFile = FileValidationController.exportValidation(saveFile);
			if(database.getPatients()!=null||database.getPatients().size()>0) {
				writeJsonToFile(database.getPatients(),saveFile);
				JOptionPane.showMessageDialog(fileChooser, "Patient details saved successfully");
			}
			else {
				JOptionPane.showMessageDialog(fileChooser, "Empty Data");
			}
		}
	}
	public void openData(PatientTableModel patientTableModel, MainFrame frame) {
		if(JOptionPane.showConfirmDialog(frame,"Do you want save current data?","Save",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
			exportData(patientTableModel);
		}
		database.getPatients().removeAll(database.getPatients());
		importData(patientTableModel);
	}
	public void newFile(PatientTableModel patientTableModel, MainFrame frame) {
		if(JOptionPane.showConfirmDialog(frame,"Do you want save current data?","Save",JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_OPTION) {
			exportData(patientTableModel);
		}
		database.getPatients().removeAll(database.getPatients());
		Controller.refreshTable(patientTableModel);
	}
	public Database getDatabase() {
		return database;
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
	public void addListenerSearchField(JTextField searchField, PatientTableModel tableModel){
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(searchField.getText().equals("")) {
					tableModel.resetData();
				}
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void addSearchButton(JButton searchButton,JTextField searchField,PatientTableModel tableModel) {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String searchTerm = searchField.getText().trim();
				if(!searchTerm.isEmpty()||searchTerm.equals("")) {
					tableModel.filterData(searchTerm);
				}else {
					tableModel.resetData();
				}
				
			}
		});
	}
}
