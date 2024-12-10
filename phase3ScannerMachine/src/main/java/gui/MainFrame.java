package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame{
	private PatientTableModel patientTableModel;
	private PatientTablePanel patientTable;
	private Controller controller;
	private AddButtonPanel addButtonPanel;
	private AcceptPanel acceptPanel;
	private SubmitListener submitListener;
	private AddUserFrame addUserFrame;
	public MainFrame() {
		super("Patient Mock Scanner");
		
		controller = new Controller();
		patientTableModel = new PatientTableModel(controller.getDb().getPatients(),controller);
		acceptPanel = new AcceptPanel();
		patientTable = new PatientTablePanel(patientTableModel,(patient)->{
			controller.addAcceptPanel(this,acceptPanel);
		});
		
		addButtonPanel = new AddButtonPanel(controller,()->{
			addUserFrame = new AddUserFrame(this,()->{
				controller.addUserAndWriteToJson(addUserFrame, patientTableModel);
			});
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
