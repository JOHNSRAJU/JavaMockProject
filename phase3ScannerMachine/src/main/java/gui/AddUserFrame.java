package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class AddUserFrame extends JFrame{
	private FormPanel formPanel;
	private SubmitButtonPanel submitButtonPanel;
	SubmitListener submitListener;
	
	public AddUserFrame(MainFrame mainFrame,SubmitListener submitListener) {
		super("Add Patient");
		this.submitListener = submitListener;
		setResizable(false);
		setSize(400, 450);
		setLocationRelativeTo(mainFrame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		formPanel = new FormPanel();
		submitButtonPanel = new SubmitButtonPanel(submitListener);
		
		setLayout(new BorderLayout());
		add(formPanel,BorderLayout.CENTER);
		add(submitButtonPanel,BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

}
