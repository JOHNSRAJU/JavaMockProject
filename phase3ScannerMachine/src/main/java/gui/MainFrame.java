package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.swing.JFrame;

import controller.Controller;
import model.Patient;

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
		patientTableModel = new PatientTableModel(controller.getDb().getPatients());
		acceptPanel = new AcceptPanel();
		patientTable = new PatientTablePanel(patientTableModel,(patient)->{
			add(acceptPanel,BorderLayout.SOUTH);
			revalidate();
			System.out.println(patient);
		});
		
		addButtonPanel = new AddButtonPanel(()->{
			addUserFrame = new AddUserFrame(this,()->{
				Patient patient = controller.createPatientObject(addUserFrame.getFormPanel().getId().getText(), addUserFrame.getFormPanel().getPatientName().getText(),addUserFrame.getFormPanel().getDateChooser().getDate(), addUserFrame.getFormPanel().getWeight().getText(), addUserFrame.getFormPanel().getPatientHeight().getText(),addUserFrame.getFormPanel().getDescriptionArea().getText(),addUserFrame);
				if(patient!=null) {
					controller.addPatientToList(patient);
					controller.writeJsonToFile(controller.getDb().getPatients());
					controller.refreshTable(patientTableModel);
					addUserFrame.dispose();
				}
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
