package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controller.Controller;
import model.Patient;

public class MainFrame extends JFrame{
	
	private PatientTableModel patientTableModel;
	private PatientTablePanel patientTablePanel;
	private Controller controller;
	private AddButtonPanel addButtonPanel;
	private AcceptPanel acceptPanel;
	private AddUserFrame addUserFrame;
	private ScanningWindow scanningWindow;
	private Patient selectedPatient;
	public MainFrame() {
		super("Patient Mock Scanner");
		controller = new Controller();
		patientTableModel = new PatientTableModel(controller.getDb().getPatients(),controller);
		patientTablePanel = new PatientTablePanel(patientTableModel,(patient)->{
			selectedPatient=patient;
			controller.addAcceptPanel(this,acceptPanel);
		});
		acceptPanel = new AcceptPanel(() ->{
			this.setEnabled(false);
			this.scanningWindow = new ScanningWindow(this,selectedPatient);
		});
		
		addButtonPanel = new AddButtonPanel(controller,()->{
			addUserFrame = new AddUserFrame(this,()->{
				controller.addUserAndWriteToJson(this,addUserFrame, patientTableModel);
			});
		});
		
		setSize(800,600);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		add(patientTablePanel,BorderLayout.CENTER);
		add(addButtonPanel,BorderLayout.NORTH);
		
		setVisible(true);
	}
	public void showValidationError(String message){
		JOptionPane.showMessageDialog(this, message,"Error",JOptionPane.OK_OPTION|JOptionPane.ERROR_MESSAGE);
	}
	public PatientTableModel getPatientTableModel() {
		return patientTableModel;
	}
	
}
