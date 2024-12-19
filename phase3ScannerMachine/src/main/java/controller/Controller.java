package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import model.Database;
import model.Patient;
import model.PatientTableModel;
public class Controller {
	static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.registerModule(new JavaTimeModule());
	}
	private static Database database = new Database();
	private JFileChooser fileChooser=new JFileChooser();
	private FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("JSON Files (*.json)", "json");
	private static final String FILEPATH = "src\\main\\resources\\patients.json";
	public Controller() {
		fileChooser.setFileFilter(fileNameExtensionFilter);
	}
	public static Database getDatabase() {
		return database;
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
		database.getPatients().add(patient);
	}
	public static void writeJsonToFile(List<Patient> array) {
		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILEPATH), array);

		} catch (IOException e) {
			System.err.println("Error writing JSON to file: " + e.getMessage());
		}
	}


	public static void refreshTable(PatientTableModel patientTableModel) {
		patientTableModel.fireTableDataChanged();
	}

}
