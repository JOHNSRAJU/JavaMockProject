package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AcceptPanel extends JPanel{
	private JButton acceptButton;
	public AcceptPanel() {
		acceptButton = new JButton("Accept");
	
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(acceptButton);
	}
}
