package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class FormPanel extends JPanel {
    public FormPanel() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gc.fill = GridBagConstraints.HORIZONTAL; // Components stretch horizontally

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
        gc.anchor = GridBagConstraints.EAST; // Align label to the right
        add(new JLabel("Patient ID:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(new JTextField(15), gc);

        // Row 2: Patient Name
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Patient Name:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(new JTextField(15), gc);

        // Row 3: Date of Birth
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Date of Birth:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        add(dateChooser, gc);

        // Row 4: Height
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Height (cm):"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(new JTextField(5), gc);

        // Row 5: Weight
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Weight (kg):"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(new JTextField(5), gc);

        // Row 6: Description
        gc.gridy++;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.NORTHEAST; // Align top-right for multi-line label
        add(new JLabel("Description:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        JTextArea descriptionArea = new JTextArea(3, 15);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        add(new JScrollPane(descriptionArea), gc);
        
        
    }
}
