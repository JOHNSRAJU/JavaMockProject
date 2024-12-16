package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.Patient;

public class AddPatientFrame extends JFrame{
	private SubmitButtonPanel submitButtonPanel;
	private UpdateButtonPanel updateButtonPanel;
	private FormPanel formPanel;
	private FormPanel updatePanel;
	public AddPatientFrame(MainFrame mainFrame,SubmitListener submitListener) {
		super("Add Patient");
		formPanel = new FormPanel();
		setView(mainFrame,this, formPanel);
		add(formPanel,BorderLayout.CENTER);
		submitButtonPanel = new SubmitButtonPanel(submitListener);
		add(submitButtonPanel,BorderLayout.SOUTH);
	}

	public AddPatientFrame(MainFrame mainFrame,Patient selectedPatient,UpdateListener updateListener) {
		// TODO Auto-generated constructor stub
		super("Update Patient");
		updatePanel = new FormPanel(selectedPatient);
		setView(mainFrame,this, formPanel);
		updateButtonPanel = new UpdateButtonPanel(updateListener,updatePanel);
		add(updateButtonPanel,BorderLayout.SOUTH);
		add(updatePanel,BorderLayout.CENTER);
	}
	private void setView(MainFrame mainFrame,AddPatientFrame patientFrame,FormPanel formPanel) {
		patientFrame.setResizable(false);
		patientFrame.setSize(400, 450);
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
