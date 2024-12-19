package gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SubmitButtonPanel extends JPanel {
	private JButton submitButton;
	public SubmitButtonPanel(SubmitListener submitListener) {
		setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		submitButton = new JButton("Submit");
		add(submitButton);
		submitButton.addActionListener((e)->{
			submitListener.submitClicked();
		});
	}
}
