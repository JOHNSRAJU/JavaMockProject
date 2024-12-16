package gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UpdateButtonPanel extends JPanel{
	private JButton updateButton;
	public UpdateButtonPanel(UpdateListener updateListener,FormPanel formPanel) {
		setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		updateButton = new JButton("Submit");
		add(updateButton);
		updateButton.addActionListener((e)->{
			updateListener.updateOccured(formPanel);
		});
	}
}
