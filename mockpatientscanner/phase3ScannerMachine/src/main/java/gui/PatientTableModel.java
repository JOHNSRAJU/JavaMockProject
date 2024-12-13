package gui;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Patient;

public class PatientTableModel extends AbstractTableModel{
	private ArrayList<Patient> patientsData;
	Controller controller;
	public PatientTableModel(ArrayList<Patient> patientsData,Controller controller) {
		super();
		this.controller=controller;
		this.patientsData = patientsData;
	}

	private String[] columnNames = {"ID","Name","Age","Weight (kg)","Height (cm)","BMI (kg/m²)","Description","Status"}; 

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return patientsData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return controller.getValueForTableModel(rowIndex,columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	public Patient getData(int row) {
		return patientsData.get(row);
	}
	
}
