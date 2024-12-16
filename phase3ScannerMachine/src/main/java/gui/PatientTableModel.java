package gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Patient;

public class PatientTableModel extends AbstractTableModel{
	private ArrayList<Patient> patientsData;
	private ArrayList<Patient> filteredData;

	Controller controller;
	public PatientTableModel(ArrayList<Patient> patientsData,Controller controller) {
		super();
		this.controller=controller;
		this.patientsData = patientsData;
		this.filteredData = patientsData;

	}

	private String[] columnNames = {"Sl No.","ID","Name","Age","Weight (kg)","Height (cm)","BMI (kg/m²)","Description","Status"}; 

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return filteredData.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return controller.getValueForTableModel(rowIndex,columnIndex, this);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	public Patient getData(int row) {
		return filteredData.get(row);
	}
	public void filterData(String searchTerm) {
		filteredData = patientsData.stream()
				.filter(person -> person.getName().toLowerCase().contains(searchTerm.toLowerCase())||
						person.getDescription().toLowerCase().contains(searchTerm.toLowerCase())||
						new Integer(person.getId()).toString().contains(searchTerm.toLowerCase())||
						person.getStatus().toString().toLowerCase().contains(searchTerm.toLowerCase()))
				.collect(Collectors.toCollection(ArrayList<Patient>::new));
fireTableDataChanged();
	}

	public ArrayList<Patient> getFilteredData() {
		return filteredData;
	}


	public void resetData() {
		filteredData = new ArrayList<Patient>(patientsData);
		fireTableDataChanged();
	}


}
