package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

public class ScanningWindow extends JFrame
{

	private JProgressBar progressBar;
	private JPanel buttonPanel;
	private boolean isPaused;
	private Thread progressBarThread;
	private JSpinner imageSpinner;
	private JComboBox<String> speedComboBox;
	private  JButton revisitButton;
	private JButton startButton;
	private JButton exitButton;
	
	public ScanningWindow()
	{
		// TODO Auto-generated constructor stub
		setTitle("Scanning window");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//patient data panel
		JPanel patientDataPanel = new JPanel(new GridBagLayout());
		patientDataPanel.setBorder(BorderFactory.createTitledBorder("Patient Data table"));
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(5, 5, 5, 5);
		gc.anchor = GridBagConstraints.WEST;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx=0.5;
		
		addLabelAndTextfield(patientDataPanel, gc, "Name:", 0);
		addLabelAndTextfield(patientDataPanel, gc, "ID:", 1);
		addLabelAndTextfield(patientDataPanel, gc, "Age:", 2);
		addLabelAndTextfield(patientDataPanel, gc, "Weight:",3);
		addLabelAndTextfield(patientDataPanel, gc, "Height:", 4);
		addLabelAndTextfield(patientDataPanel, gc, "BMI:", 5);
		addLabelAndTextfield(patientDataPanel, gc, "Description:",6);
		addLabelAndTextfield(patientDataPanel, gc, "Status:",7);
		
		add(patientDataPanel,BorderLayout.WEST);
		
		//Scanning progress panel
		JPanel scanningPanel = new JPanel();
		scanningPanel.setLayout(new GridLayout(5, 1));
		scanningPanel.setBorder(BorderFactory.createTitledBorder("Scanning progress"));
		
		 progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		scanningPanel.add(progressBar);
		
		JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imagePanel.add(new JLabel("No of images"));
		 imageSpinner = new JSpinner(new SpinnerNumberModel(25, 1, 50, 1));
		imagePanel.add(imageSpinner);
		scanningPanel.add(imagePanel);
		
		JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		speedPanel.add(new JLabel("Rotation speed"));
		 speedComboBox = new JComboBox<>(new String[] {"05","01","02","03","04","05"});
		speedPanel.add(speedComboBox);
		scanningPanel.add(speedPanel);
		
		add(scanningPanel,BorderLayout.CENTER);
		
		//buttons panel
		 buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		 startButton = new JButton("Start");
		 exitButton = new JButton("Exit");
		buttonPanel.add(startButton);
		buttonPanel.add(exitButton);
		//buttonPanel.add(new JButton("Exit"));
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToPauseButton();
				startProgressBar();
			}
		});
		
		add(buttonPanel,BorderLayout.SOUTH);
		
		//startProgressBar();
	}
	
	private void addLabelAndTextfield(JPanel panel,GridBagConstraints gbc,String labelText,int row)
	{
		gbc.gridx=0;
		gbc.gridy=row;
		panel.add(new JLabel(labelText),gbc);
		
		gbc.gridx=1;
		JTextField textField = new JTextField(15);
		textField.setPreferredSize(new Dimension(200, 25));
		panel.add(textField,gbc);
	}
	
	private void startProgressBar()
	{ 
		isPaused = false;
		int noOfImages = (int) imageSpinner.getValue();
		int rotationSpeed = Integer.parseInt((String) speedComboBox.getSelectedItem());
	
		progressBarThread = new Thread(()->{
			
			for(int i=progressBar.getValue();i<=100;i++)
				{
					if(isPaused)break;
					final int percent =i;
					//updates to the progressbar are made on edt
					
					SwingUtilities.invokeLater(()->{
					progressBar.setValue(percent);
					if(percent==100)
					{
						progressBar.setForeground(Color.GREEN);
						progressBar.setString("Completed");
					    JButton completeButton = new JButton("Complete");
					    JButton revisitButton = new JButton("Revisit");
					    buttonPanel.removeAll();
					    
					    buttonPanel.add(revisitButton);
					    buttonPanel.add(completeButton);
					    buttonPanel.revalidate();
						buttonPanel.repaint();
						JOptionPane.showMessageDialog(this, "Scan Successfully completed","success",JOptionPane.INFORMATION_MESSAGE);
					}
					});
					try
					{
						int speed = Math.max(1, (int)Math.round((double)noOfImages/rotationSpeed*100));
						Thread.sleep(speed);
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
				}
		
		});
		progressBarThread.start();
	}
	
	private void switchToPauseButton()
	{
		buttonPanel.removeAll();
		JButton pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switchToRevisitAndRetake();
				isPaused = true;
				
			}
		});
		buttonPanel.add(pauseButton);
		buttonPanel.revalidate();
		buttonPanel.repaint();
	}
	
	private void switchToRevisitAndRetake()
	{
	      buttonPanel.removeAll();
	      revisitButton = new JButton("Revisit");
	      JButton resumeButton = new JButton("Resume");
	      buttonPanel.add(revisitButton);
	      buttonPanel.add(resumeButton);
	      buttonPanel.revalidate();
		  buttonPanel.repaint();
		  
		  resumeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				switchToPauseButton();
				startProgressBar();
				
			}
		});
		  
		  
		  revisitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				buttonPanel.removeAll();
				buttonPanel.add(startButton);
				buttonPanel.add(exitButton);
				buttonPanel.revalidate();
				buttonPanel.repaint();
				progressBar.setValue(0);
				
			}
		});
		  
	}	
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(()->{
			ScanningWindow scanningWindow = new ScanningWindow();
			scanningWindow.setVisible(true);
		}
		);
	}
	
}
