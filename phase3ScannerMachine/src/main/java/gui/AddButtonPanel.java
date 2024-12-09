package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddButtonPanel extends JPanel{
	private JButton addPatientButton;
	AddPatientListener addPatientListener;
	AddButtonPanel(AddPatientListener addPatientListener){
		this.addPatientListener = addPatientListener;
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		addPatientButton = new JButton("+   Add Patient");
		addPatientButton.setFont(new Font("Arial", Font.BOLD, 12));
        addPatientButton.setForeground(Color.WHITE);
        addPatientButton.setBackground(new Color(34, 139, 34)); // Forest Green
        addPatientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        addPatientButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				addPatientButton.setBackground(new Color(50, 205, 50));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addPatientButton.setBackground(new Color(34, 139, 34));
				
			}
        	
        });
        
        addPatientButton.addActionListener((event)->{
        	if(event.getSource()==addPatientButton) {
        		addPatientListener.addPatientClicked();
        	}
        });
        add(addPatientButton);
	}
}
