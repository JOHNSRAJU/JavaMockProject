package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.Patient;

public class MenuBar extends JMenuBar{
	private JMenu file;
	private JMenuItem importMenu;
	private JMenuItem exportMenu;
	private JMenuItem exitMenu;
	public MenuBar(PatientTableModel patientTableModel,Controller controller) {
		file = new JMenu("File");
		importMenu = new JMenuItem("Import");
		exportMenu = new JMenuItem("Export");
		exitMenu = new JMenuItem("Exit");
		
		importMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setDialogTitle("Import Patient");
				int returnValue = fileChooser.showOpenDialog(fileChooser);
				
				if (returnValue==JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					ArrayList<Patient> importedPatients = Controller.readJsonFile(selectedFile);
					if(importedPatients!=null) {
						controller.getDb().getPatients().addAll(importedPatients);
						controller.refreshTable(patientTableModel);
						controller.writeJsonToFile(controller.getDb().getPatients());
						JOptionPane.showMessageDialog(fileChooser, "Patient details successfully imported");
					}else {
						JOptionPane.showMessageDialog(fileChooser, "error");
					}
					
				}
			}
		});
		exportMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setDialogTitle("Export Patient");
				int returnValue = fileChooser.showOpenDialog(fileChooser);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					Controller.writeJsonToFile(controller.getDb().getPatients());
					JOptionPane.showMessageDialog(fileChooser, "Patients supported successfully");
				}
			}
		});
		exitMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
		
		setFont(new Font("sans-serif",Font.ITALIC, 12));
		
		file.add(importMenu);
		file.add(exportMenu);
		file.add(new JSeparator());
		file.add(exitMenu);
		add(file);
		
	}
}
