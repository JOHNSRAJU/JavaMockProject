package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame{
	private PatientTableModel patientTableModel;
	private PatientTablePanel patientTable;
	private Controller controller;
	private AddButtonPanel addButtonPanel;
	private AcceptPanel acceptPanel;
	public MainFrame() {
		super("Patient Mock Scanner");
		
		controller = new Controller();
		patientTableModel = new PatientTableModel(controller.getDb().getPatients());
		acceptPanel = new AcceptPanel();
		patientTable = new PatientTablePanel(patientTableModel,(patient)->{
			add(acceptPanel,BorderLayout.SOUTH);
			revalidate();
			System.out.println(patient);
		});
		
		addButtonPanel = new AddButtonPanel(()->{
			new AddUserFrame(this);
		});
		
		setVisible(true);
		setSize(800,600);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(patientTable,BorderLayout.CENTER);
		add(addButtonPanel,BorderLayout.NORTH);
	}
}
