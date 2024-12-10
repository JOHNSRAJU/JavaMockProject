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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gui.AcceptPanel;
import gui.AddPatientListener;
import gui.AddUserFrame;
import gui.MainFrame;
import gui.PatientTableModel;
import model.Database;
import model.Patient;
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
	public void writeJsonToFile(List<Patient> array) {
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
		return Math.round((weight*10000)/(height*height));
	}
	
	public Patient createPatientObject(String id,String name,Date dob, String weight,String height, String description,JFrame frame) {
		if(id!=null&&name!=null&&dob!=null&&weight!=null&&height!=null) {
			Patient patient = new Patient(Integer.parseInt(id), name, dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), Double.parseDouble(weight), Double.parseDouble(height), description);
			System.out.println(patient);
			return patient;
		}
		JOptionPane.showMessageDialog(frame, "Enter Full details : ","Error",JOptionPane.OK_OPTION|JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	public void refreshTable(PatientTableModel patientTableModel) {
		patientTableModel.fireTableDataChanged();
	}
	public void addAcceptPanel(MainFrame frame,AcceptPanel acceptPanel) {
		frame.add(acceptPanel,BorderLayout.SOUTH);
		frame.revalidate();
	}
	public void addUserAndWriteToJson(AddUserFrame addUserFrame,PatientTableModel patientTableModel) {
		Patient patient = createPatientObject(addUserFrame.getFormPanel().getId().getText(), addUserFrame.getFormPanel().getPatientName().getText(),addUserFrame.getFormPanel().getDateChooser().getDate(), addUserFrame.getFormPanel().getWeight().getText(), addUserFrame.getFormPanel().getPatientHeight().getText(),addUserFrame.getFormPanel().getDescriptionArea().getText(),addUserFrame);
		if(patient!=null) {
			addPatientToList(patient);
			writeJsonToFile(getDb().getPatients());
			refreshTable(patientTableModel);
			addUserFrame.dispose();
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
		Patient patient = db.getPatients().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return patient.getId();
		case 1:
			return patient.getName();
		case 2:
			return LocalDate.now().getYear()-patient.getDob().getYear();
		case 3:
			return patient.getWeight();
		case 4:
			return patient.getHeight();
		case 5:
			return patient.getBmi();
		case 6:
			return patient.getDescription();
		case 7:
			return patient.getStatus();
		}
		return null;
	}
}