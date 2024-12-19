package controller;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import gui.MainFrame;
import gui.ScanningWindow;
import model.Database;
import model.Patient;
import model.PatientTableModel;
import model.Status;

public class ScanningController {
	Database database = Controller.getDatabase();
	
	public static void addExitButtonListener(JButton exitButton,JProgressBar progressBar, ScanningWindow window, MainFrame mainFrame) {
		exitButton.addActionListener((e)->{
			if (progressBar.getValue() < 100) {
				int response = JOptionPane.showConfirmDialog(
						window, 
						"The scan is not complete. Are you sure you want to exit?", 
						"Confirm Exit", 
						JOptionPane.YES_NO_OPTION, 
						JOptionPane.WARNING_MESSAGE
						);
				if (response == JOptionPane.NO_OPTION) {
					window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else {
					mainFrame.setEnabled(true);
					window.dispose();
				}
			} else {
				window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
	}
	public static void addCompleteButtonListener(JButton completeButton,ScanningWindow window, MainFrame mainFrame,Patient patient,PatientTableModel tableModel) {
		completeButton.addActionListener((event)->{
			ArrayList<Patient> patients = Controller.readJsonFromFile();
			patient.setStatus(Status.COMPLETED);
			Controller.writeJsonToFile(patients);
			mainFrame.setEnabled(true);
			tableModel.fireTableDataChanged();
			window.dispose();
		});
	}
}

