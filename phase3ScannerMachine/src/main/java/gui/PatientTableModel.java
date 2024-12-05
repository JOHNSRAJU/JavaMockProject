package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Patient;

public class PatientTableModel extends AbstractTableModel{
	private ArrayList<Patient> patientsData;
	


	public PatientTableModel(ArrayList<Patient> patientsData) {
		super();
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
		Patient patient = patientsData.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return patient.getId();
		case 1:
			return patient.getName();
		case 2:
			return patient.getAge();
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

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	
	
}
