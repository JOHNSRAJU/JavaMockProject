package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;

public class Database {
	private ArrayList<Patient> patients;
	private Controller controller;
	public Database() {
//		controller = new Controller();
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
		patients = Controller.readJsonFromFile();
		if(patients==null) {
			patients = new ArrayList<Patient>();
		}
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
}
