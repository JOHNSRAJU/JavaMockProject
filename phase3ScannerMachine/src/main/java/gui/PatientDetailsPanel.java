package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Patient;

public class PatientDetailsPanel extends JPanel{
	
	private JTextField nameField;
	private JTextField idField;
	private JTextField ageField;
	private JTextField weightField;
	private JTextField heightField;
	private JTextField bmiField;
	private JTextArea descriptionField;
	private JTextField statusField;
	
	
	public PatientDetailsPanel(Patient patient) {
		System.out.println(patient);
		nameField = new JTextField(patient.getName().trim());
		nameField.setEditable(false);
		idField = new JTextField(Integer.toString(patient.getId()));
		idField.setEditable(false);
		ageField = new JTextField(Integer.toString(LocalDate.now().getYear()-patient.getDob().getYear()));
		ageField.setEditable(false);
		weightField = new JTextField(Double.toString(patient.getWeight()));
		weightField.setEditable(false);
		heightField = new JTextField(Double.toString(patient.getHeight()));
		heightField.setEditable(false);
		bmiField = new JTextField(Double.toString(patient.getBmi()));
		bmiField.setEditable(false);
		descriptionField=new JTextArea(patient.getDescription(), 3, 10);
		descriptionField.setEditable(false);
		statusField = new JTextField(patient.getStatus().toString());
		statusField.setEditable(false);
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Patient Data table"));
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx=0.5;
		
		addLabel(gc, "Name:", 0);
		addLabel(gc, "ID:", 1);
		addLabel(gc, "Age:", 2);
		addLabel(gc, "Weight:",3);
		addLabel(gc, "Height:", 4);
		addLabel(gc, "BMI:", 5);
		addLabel(gc, "Description:",6);
		addLabel(gc, "Status:",7);
		
		
		addTextField(gc, nameField, 0);
		addTextField(gc, idField, 1);
		addTextField(gc, ageField, 2);
		addTextField(gc, weightField, 3);
		addTextField(gc, heightField, 4);
		addTextField(gc, bmiField, 5);
		addTextArea(gc, descriptionField, 6);
		addTextField(gc, statusField, 7);
		
		
	}
	
	private void addLabel(GridBagConstraints gbc,String labelText,int row){
		gbc.gridx=0;
		gbc.gridy=row;
		add(new JLabel(labelText),gbc);
	}
	private void addTextField(GridBagConstraints gbc,JTextField textField,int row){
		gbc.gridx=1;
		gbc.gridy=row;
		add(textField,gbc);
	}
	private void addTextArea(GridBagConstraints gbc,JTextArea textArea,int row) {
		gbc.gridx=1;
		gbc.gridy=row;
		JScrollPane scrollBar = new JScrollPane(textArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollBar,gbc);
	}
}
