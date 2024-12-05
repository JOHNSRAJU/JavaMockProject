package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Patient;

public class PatientTablePanel extends JPanel{
	private JTable patientTable;
	private PatientTableModel patientTableModel;
	ArrayList<Patient> patientsData;
	
	public PatientTablePanel(ArrayList<Patient> patientsData) {
		this.patientsData = patientsData;
		patientTableModel = new PatientTableModel(patientsData);
		patientTable = new JTable(patientTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(patientTable),BorderLayout.CENTER);
	}
	
	public void refresh() {
		patientTableModel.fireTableDataChanged();
	}
	
}
