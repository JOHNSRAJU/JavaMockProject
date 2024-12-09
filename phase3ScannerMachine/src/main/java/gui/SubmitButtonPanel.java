package gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SubmitButtonPanel extends JPanel {
	JButton submitButton;
	public SubmitButtonPanel() {
		setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		submitButton = new JButton("Submit");
		add(submitButton);
	}
}
