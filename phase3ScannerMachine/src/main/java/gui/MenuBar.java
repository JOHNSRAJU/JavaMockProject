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
import controller.PatientTableController;
import model.Patient;
import model.PatientTableModel;

public class MenuBar extends JMenuBar{
	private JMenu file;
	private JMenuItem importMenu;
	private JMenuItem exportMenu;
	private JMenuItem exitMenu;
	private JMenuItem newFile;
	private JMenuItem openFile;
	public MenuBar(PatientTableModel patientTableModel,PatientTableController controller, MainFrame frame) {
		file = new JMenu("File");
		importMenu = new JMenuItem("Import");
		exportMenu = new JMenuItem("Export");
		exitMenu = new JMenuItem("Exit");
		newFile = new JMenuItem("New File");
		openFile = new JMenuItem("Open File");
		importMenu.addActionListener((e)->controller.importData(patientTableModel));
		exportMenu.addActionListener((e)->controller.exportData(patientTableModel));
		exitMenu.addActionListener(e->System.exit(0));
		newFile.addActionListener((e)->controller.newFile(patientTableModel, frame));
		openFile.addActionListener((e)->controller.openData(patientTableModel, frame));

		setFont(new Font("sans-serif",Font.ITALIC, 12));
		file.add(newFile);
		file.add(openFile);
		file.addSeparator();
		file.add(importMenu);
		file.add(exportMenu);
		file.add(new JSeparator());
		file.add(exitMenu);
		add(file);
	}
}
