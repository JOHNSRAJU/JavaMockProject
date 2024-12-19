package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gui.AddPatientFrame;
import gui.FormPanel;
import model.Database;
import model.Patient;
import model.PatientTableModel;

public class AddEditPatientController {
	private Database database = Controller.getDatabase();
	
	public void addPatientToList(Patient patient) {
		database.getPatients().add(patient);
	}
	
	public static double calculateBMI(double weight,double height) {
		if(height<0) {
			throw new IllegalArgumentException("Height should greater than Zero");
		}
		else if(weight<0) {
			throw new IllegalArgumentException("Weight should greater than Zero");
		}
		return Double.parseDouble(String.format("%.2f",(weight*10000)/(height*height)));
	}
	public Patient createPatientObject(String id,String name,Date dob, String weight,String height, String description,JFrame frame) {
		if(id!=null&&name!=null&&dob!=null&&weight!=null&&height!=null) {
			Patient patient = new Patient(Integer.parseInt(id), name, dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), Double.parseDouble(weight), Double.parseDouble(height), description);
			return patient;
		}
		return null;
	}
	public void addPatientAndWriteToJson(AddPatientFrame addPatientFrame,PatientTableModel patientTableModel) {
		if(validateForm(addPatientFrame.getFormPanel(), addPatientFrame)) {
			Patient patient = createPatientObject(addPatientFrame.getFormPanel().getId().getText(), addPatientFrame.getFormPanel().getPatientName().getText(),addPatientFrame.getFormPanel().getDateChooser().getDate(), addPatientFrame.getFormPanel().getWeight().getText(), addPatientFrame.getFormPanel().getPatientHeight().getText(),addPatientFrame.getFormPanel().getDescriptionArea().getText(),addPatientFrame);
			if(patient!=null) {
				addPatientToList(patient);
				Controller.writeJsonToFile(database.getPatients());
				Controller.refreshTable(patientTableModel);
				addPatientFrame.dispose();
			}
		}
	}
	
	private boolean validateForm(FormPanel formPanel, JFrame frame) {

		try {
			int id = Integer.parseInt(formPanel.getId().getText());
			if (id <= 0) {
				showError("ID must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("ID must be a valid number.",frame);
			return false;
		}
		if (formPanel.getPatientName().getText().isEmpty()) {
			showError("Name is required.",frame);
			return false;
		}

		if(formPanel.getDateChooser().getDate() == null){
			showError("Date of Birth is required", frame);
			return false;
		}
		LocalDate dob = formPanel.getDateChooser().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (dob == null) {
			showError("Invalid Date",frame);
			return false;
		}
		else if((LocalDate.now().compareTo(dob))==-1) {
			showError("Invalid Date",frame);
			return false;
		}
		try {
			double height = Double.parseDouble(formPanel.getPatientHeight().getText());
			if (height <= 0) {
				showError("Height must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("Height must be a valid number.",frame);
			return false;
		}

		try {
			double weight = Double.parseDouble(formPanel.getWeight().getText());
			if (weight <= 0) {
				showError("Weight must be a positive number.",frame);
				return false;
			}
		} catch (NumberFormatException e) {
			showError("Weight must be a valid number.",frame);
			return false;
		}
		if (formPanel.getDescriptionArea().getText().length()>200) {
			showError("Description should below 200 characters.",frame);
			return false;
		}

		return true; // If all checks passed
	}
	
	private void showError(String message, JFrame frame) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.OK_OPTION|JOptionPane.ERROR_MESSAGE);	    
	}
	public void updateForm(AddPatientFrame addPatientFrame,PatientTableModel patientTableModel,FormPanel formPanel,Patient patient) {
		if(JOptionPane.showConfirmDialog(addPatientFrame,"Are you sure to update?", "Confirm Update",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			if(validateForm(formPanel, addPatientFrame)) {
				patient.setId(Integer.parseInt(formPanel.getId().getText()));
				patient.setName(formPanel.getPatientName().getText());
				patient.setDob(formPanel.getDateChooser().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				patient.setHeight(Double.parseDouble(formPanel.getPatientHeight().getText()));
				patient.setWeight(Double.parseDouble(formPanel.getWeight().getText()));
				patient.setBmi(calculateBMI(patient.getWeight(), patient.getHeight()));
				patient.setDescription(formPanel.getDescriptionArea().getText());
				Controller.writeJsonToFile(database.getPatients());
				Controller.refreshTable(patientTableModel);
				addPatientFrame.dispose();
			}
			else {
				JOptionPane.showMessageDialog(addPatientFrame, "Error","Not updated!",JOptionPane.OK_OPTION);
			}
		}
		else {
			addPatientFrame.dispose();
		}
		
	}
	public static void addDocumentListenerDescription(JTextArea descriptionArea,FormPanel formPanel){
		descriptionArea.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(descriptionArea.getText().length()>200) {
					JOptionPane.showMessageDialog(formPanel, "Description should be below 200 characters.","Description Error",JOptionPane.OK_OPTION|JOptionPane.WARNING_MESSAGE);
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
