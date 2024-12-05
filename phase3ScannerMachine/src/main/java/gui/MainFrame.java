package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame{
	PatientTablePanel patientTable;
	Controller controller;
	public MainFrame() {
		super("Patient Mock Scanner");
		controller = new Controller();
		patientTable = new PatientTablePanel(controller.db.patients);
		
		setVisible(true);
		setSize(500,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(patientTable,BorderLayout.CENTER);
	}
}
