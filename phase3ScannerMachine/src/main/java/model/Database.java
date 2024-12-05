package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database {
	public ArrayList<Patient> patients;
	private String filePath = "src\\main\\resources\\patients.json";
	public Database() {
//		Patient person = new Patient(1,"John Doe", 30,31,30, "New");
//        Patient person1 = new Patient(1,"Johns Raju", 30,31,30, "New");
//        Patient person2 = new Patient(1,"Joel James", 30,31,30, "New");
//        ArrayList<Patient> array = new ArrayList<Patient>();
//        array.add(person);
//        array.add(person1);
//        array.add(person2);
//
//        // Serialize the Person object to JSON and save to file
//        writeJsonToFile(filePath, array);
		patients = readJsonFromFile(filePath);
		if(patients==null) {
			patients = new ArrayList<Patient>();
		}
	}
	
	public static ArrayList<Patient> readJsonFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<Patient>>() {});
        } catch (IOException e) {
            System.err.println("Error reading JSON from file: " + e.getMessage());
        }
        return null;
    }
	public static void writeJsonToFile(String filePath, List<Patient> array) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), array);
            System.out.println("JSON written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }
    }
}
