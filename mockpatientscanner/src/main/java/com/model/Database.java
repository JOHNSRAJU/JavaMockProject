package com.model;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;

public class Database
{

		// TODO Auto-generated method stub
		
	    //databse is actually json file and arraylist
		private String filepath = "src\\main\\resources\\patients.json";
		public ArrayList<Patient> patients;
		
//		Patient patient1 = new Patient(1, "John", 30.0, 150, "asthma", "new");
//		Patient patient2 = new Patient(2, "Doe", 32.0, 160, "asthma", "new");
//		Patient patient3 = new Patient(3, "Ann", 34.0, 155, "asthma", "completed");
//		
//		ArrayList<Patient>  arrayPatient = new ArrayList();
//		arrayPatient.add(patient1);
//		arrayPatient.add(patient2);
//		arrayPatient.add(patient3);
//		
//		writeToJsonfile(filepath,arrayPatient);
//		
//		ArrayList<Patient> deserialisedPatient =  readJsonFile(filepath);
//		for(Patient eachPatient : deserialisedPatient)
//		{
//			System.out.println(eachPatient);
//		}
		
		
		public Database()
		{
			// TODO Auto-generated constructor stub
			patients = readJsonFile(filepath);
			
			if(patients==null)
			{
				patients = new ArrayList<Patient>();
			}
		}
		
		
	private static ArrayList<Patient> readJsonFile(String filepath)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(new File(filepath), new TypeReference<ArrayList<Patient>>() {});
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
		
	}

	private static void writeToJsonfile(String filepath, ArrayList<Patient> arrayPatient) {
		// TODO Auto-generated method stub
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File(filepath), arrayPatient);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public ArrayList<Patient> getPatients() {
		return patients;
	}


	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
	
	

}
