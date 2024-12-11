package com.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.controller.Controller;

public class MainFrame extends JFrame
{
	PatientTablePanel PatientTablePanel;
	Controller controller;
	
	public MainFrame()
	{
		super("Patient Mock Scanner");
		controller = new Controller();
		PatientTablePanel = new PatientTablePanel(controller.db.patients);
		
		//JButton addButton = new JButton("Add button");
		
		ImageIcon originalIcon = new ImageIcon("src\\main\\resources\\add.jpg");
		Image scaledImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		JButton addButton = new JButton(scaledIcon);
		addButton.setToolTipText("Add patient");
		
		JButton acceptButton = new JButton("Accept");
		
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topPanel.add(addButton);
		bottomPanel.add(acceptButton);
		
		setVisible(true);
		setSize(500,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(PatientTablePanel,BorderLayout.CENTER);
		add(topPanel,BorderLayout.NORTH);
		add(bottomPanel,BorderLayout.SOUTH);
	}
}
