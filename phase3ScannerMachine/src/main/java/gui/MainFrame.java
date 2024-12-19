package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;
import controller.PatientTableController;
import model.Patient;
import model.PatientTableModel;

public class MainFrame extends JFrame{
	
	private PatientTableModel patientTableModel;
	private PatientTablePanel patientTablePanel;
	private PatientTableController tableController;
	private AddButtonPanel addButtonPanel;
	private AcceptEditPanel acceptEditPanel;
	private AddPatientFrame addUserFrame;
	private ScanningWindow scanningWindow;
	private Patient selectedPatient;
	private MenuBar menuBar;
	
	public MainFrame() {
		super("Patient Mock Scanner");
		setupTable();
		setupAcceptEditPanel();
		setupMenuBar();
		setView();
		
		setLayout(new BorderLayout());
		add(patientTablePanel,BorderLayout.CENTER);
		add(addButtonPanel,BorderLayout.NORTH);
		setJMenuBar(menuBar);
		
		setVisible(true);
	}
	public void showValidationError(String message){
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.OK_OPTION|JOptionPane.ERROR_MESSAGE);
	}
	public PatientTableModel getPatientTableModel() {
		return patientTableModel;
	}
	
	private void setupTable() {
		tableController = new PatientTableController(patientTableModel);
		patientTableModel = new PatientTableModel(tableController.getDatabase().getPatients(),tableController);
		patientTablePanel = new PatientTablePanel(patientTableModel,(patient)->{
			//Patient Select Listener Implementation
			selectedPatient=patient;
			tableController.addAcceptPanel(this,acceptEditPanel);
		});
	}
	
	private void setupAcceptEditPanel() {
		acceptEditPanel = new AcceptEditPanel(() ->{
			//AcceptButtonListener Implementation
			this.setEnabled(false);
			this.scanningWindow = new ScanningWindow(this,selectedPatient);
		},()->{
			//EditButtonListener Implementation
			addUserFrame = new AddPatientFrame(this,patientTableModel,selectedPatient);
		});
	}
	
	private void setupMenuBar() {
		menuBar = new MenuBar(patientTableModel,tableController,this);
		addButtonPanel = new AddButtonPanel(tableController,()->{
			addUserFrame = new AddPatientFrame(this,patientTableModel);
		},patientTableModel);
	}
	
	private void setView() {
		setSize(800,600);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
}
