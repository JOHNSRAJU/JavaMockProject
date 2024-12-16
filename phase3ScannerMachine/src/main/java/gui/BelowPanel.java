package gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Patient;

public class BelowPanel extends JPanel{
	private JButton acceptButton;
	private JButton editButton;
	public BelowPanel(AcceptButtonListener acceptButtonListener, EditButtonListener editButtonListener) {
		acceptButton = new JButton("Accept");
		editButton = new JButton("Edit");
		setBorder(BorderFactory.createEmptyBorder(0,0,5,10));
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(editButton);
		add(acceptButton);
		
		acceptButton.addActionListener((event) -> acceptButtonListener.onAcceptClicked());
		editButton.addActionListener((e)-> editButtonListener.editButtonClicked());
	}
}
