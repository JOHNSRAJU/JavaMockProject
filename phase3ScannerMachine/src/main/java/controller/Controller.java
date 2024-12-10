package controller;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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

}