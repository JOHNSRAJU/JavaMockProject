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
