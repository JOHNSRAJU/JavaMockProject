package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class AddUserFrame extends JFrame{
	FormPanel formPanel;
	SubmitButtonPanel submitButtonPanel;
	
	public AddUserFrame(MainFrame mainFrame) {
		super("Add Patient");
		setVisible(true);
		setResizable(false);
		setSize(400, 450);
		setLocationRelativeTo(mainFrame);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		formPanel = new FormPanel();
		submitButtonPanel = new SubmitButtonPanel();
		
		setLayout(new BorderLayout());
		add(formPanel,BorderLayout.CENTER);
		add(submitButtonPanel,BorderLayout.SOUTH);
	}
}
