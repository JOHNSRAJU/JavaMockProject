package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import controller.AddEditPatientController;
import model.Patient;
import model.PatientTableModel;

public class AddPatientFrame extends JFrame{
	private SubmitButtonPanel submitButtonPanel;
	private UpdateButtonPanel updateButtonPanel;
	private FormPanel formPanel;
	private FormPanel updatePanel;
	private AddEditPatientController addEditcontroller = new AddEditPatientController();;
	public AddPatientFrame(MainFrame mainFrame,PatientTableModel patientTableModel) {
		super("Add Patient");
		formPanel = new FormPanel();
		setView(mainFrame,this, formPanel);
		add(formPanel,BorderLayout.CENTER);
		submitButtonPanel = new SubmitButtonPanel(()->{
			addEditcontroller.addPatientAndWriteToJson(this, patientTableModel);
		});
		add(submitButtonPanel,BorderLayout.SOUTH);
	}

	public AddPatientFrame(MainFrame mainFrame,PatientTableModel patientTableModel,Patient selectedPatient) {
		// TODO Auto-generated constructor stub
		super("Update Patient");
		updatePanel = new FormPanel(selectedPatient);
		setView(mainFrame,this, formPanel);
		updateButtonPanel = new UpdateButtonPanel((updatePanel)->{
			addEditcontroller.updateForm(this, patientTableModel, updatePanel, selectedPatient);
		},updatePanel);
		add(updateButtonPanel,BorderLayout.SOUTH);
		add(updatePanel,BorderLayout.CENTER);
	}
	private void setView(MainFrame mainFrame,AddPatientFrame patientFrame,FormPanel formPanel) {
		patientFrame.setResizable(false);
		patientFrame.setSize(400, 550);
		patientFrame.setLocationRelativeTo(mainFrame);
		patientFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		patientFrame.setLayout(new BorderLayout());
		
		
		
		patientFrame.setVisible(true);
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public FormPanel getUpdatePanel() {
		return updatePanel;
	}
	

}
