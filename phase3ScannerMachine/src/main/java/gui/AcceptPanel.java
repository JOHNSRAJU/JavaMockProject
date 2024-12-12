package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Patient;

public class AcceptPanel extends JPanel{
	private JButton acceptButton;
	public AcceptPanel(AcceptPanelListener acceptPanelListener) {
		acceptButton = new JButton("Accept");
	
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(acceptButton);
		
		acceptButton.addActionListener((event) -> acceptPanelListener.onAcceptClicked());
	}
}
