package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Controller;

public class AddButtonPanel extends JPanel{
	private JButton addPatientButton;
	AddPatientListener addPatientListener;
	private JButton searchButton;
	private JTextField searchField;
	private PatientTableModel patientTableModel;
	AddButtonPanel(Controller controller,AddPatientListener addPatientListener, PatientTableModel patientTableModel){
		this.addPatientListener = addPatientListener;
		this.patientTableModel = patientTableModel;
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		addPatientButton = new JButton("+   Add Patient");
		addPatientButton.setFont(new Font("Arial", Font.BOLD, 12));
        addPatientButton.setForeground(Color.WHITE);
        addPatientButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addPatientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 12));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(70, 130, 180));
        searchField.setPreferredSize(searchButton.getPreferredSize());
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        controller.addMouseAndActionListener(addPatientButton, addPatientListener);
        
        add(searchField);
        add(searchButton);
        add(addPatientButton);
        
        searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String searchTerm = searchField.getText().trim();
				if(!searchTerm.isEmpty()||searchTerm.equals("")) {
					filterTableData(searchTerm);
				}else {
					resetTableData();
				}
				
			}
		});
	}
	private void filterTableData(String searchTerm) {
		patientTableModel.filterData(searchTerm);
	}
	private void resetTableData() {
		patientTableModel.resetData();
	}
}
