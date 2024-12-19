package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import controller.AddEditPatientController;
import controller.Controller;
import model.Patient;

public class FormPanel extends JPanel {
	private JTextField id;
	private JTextField name;
	private JDateChooser dateChooser;
	private JTextField weight;
	private JTextField height;
	private JTextArea descriptionArea;
    public FormPanel() {
        setView();
        AddEditPatientController.AddIdListener(id,this);
    }
	public FormPanel(Patient selectedPatient) {
		setView();
		id.setText(new Integer(selectedPatient.getId()).toString());
		name.setText(selectedPatient.getName());
		dateChooser.setDate(Date.from(selectedPatient.getDob().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		weight.setText(new Double(selectedPatient.getWeight()).toString());
		height.setText(new Double(selectedPatient.getHeight()).toString());
		descriptionArea.setText(selectedPatient.getDescription());
		AddEditPatientController.addDocumentListenerDescription(descriptionArea, this);
	}
	public JTextField getId() {
		return id;
	}
	public JTextField getPatientName() {
		return name;
	}
	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	public JTextField getWeight() {
		return weight;
	}
	public JTextField getPatientHeight() {
		return height;
	}
	public JTextArea getDescriptionArea() {
		return descriptionArea;
	}
	private void setView() {
		id = new JTextField(15);
    	name = new JTextField(15);
    	dateChooser = new JDateChooser();
    	weight = new JTextField(15);
    	height = new JTextField(15);
    	descriptionArea = new JTextArea(6, 15);
//    	descriptionArea.setLineWrap(true);
//    	descriptionArea.setWrapStyleWord(true);
//    	descriptionArea.setPreferredSize(new Dimension(200,60));
    	dateChooser.setSize(350, 300);
    	
    	
    	
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gc.fill = GridBagConstraints.HORIZONTAL; // Components stretch horizontally
        gc.weightx = 1.0;
        gc.weighty= 1.0;
        gc.fill=GridBagConstraints.BOTH;
        // Title: Patient Form
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2; // Span across two columns
        gc.anchor = GridBagConstraints.CENTER;
        JLabel titleLabel = new JLabel("Patient Form");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20)); // Set font size and style
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, gc);

        // Row 1: Patient ID
        gc.gridy++;
        gc.gridwidth = 1; // Reset gridwidth
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START; // Align label to the right
        add(new JLabel("Patient ID:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(id, gc);

        // Row 2: Patient Name
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel("Patient Name:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(name, gc);

        // Row 3: Date of Birth
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel("Date of Birth:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;

        dateChooser.setDateFormatString("dd-MM-yyyy");
        add(dateChooser, gc);

        // Row 4: Height
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel("Height (cm):"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(height, gc);

        // Row 5: Weight
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JLabel("Weight (kg):"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(weight, gc);

        // Row 6: Description
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START; // Align top-right for multi-line label
        add(new JLabel("Description:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        add(new JScrollPane(descriptionArea), gc);
        
	}
}
