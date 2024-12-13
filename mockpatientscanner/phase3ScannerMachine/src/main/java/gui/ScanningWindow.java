package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import controller.Controller;
import model.Patient;
import model.Status;

public class ScanningWindow extends JDialog{

	private JProgressBar progressBar;
	private JPanel buttonPanel;
	private boolean isPaused;
	private Thread progressBarThread;
	private JSpinner imageSpinner;
	private JComboBox<String> speedComboBox;
	private JButton revisitButton;
	private JButton startButton;
	private JButton exitButton;
	private JButton completeButton;

	public ScanningWindow(MainFrame mainFrame, Patient patient){
		// TODO Auto-generated constructor stub
		
		completeButton = new JButton("Complete");
		revisitButton = new JButton("Revisit");
		
		setTitle("Scanning window");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setAlwaysOnTop(true);

		PatientDetailsPanel patientDataPanel = new PatientDetailsPanel(patient);
		add(patientDataPanel,BorderLayout.WEST);

		//Scanning progress panel
		JPanel scanningPanel = new JPanel();
		scanningPanel.setLayout(new GridLayout(5, 1));
		scanningPanel.setBorder(BorderFactory.createTitledBorder("Scanning progress"));

		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setStringPainted(true);
		scanningPanel.add(progressBar);

		JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imagePanel.add(new JLabel("No of images"));
		imageSpinner = new JSpinner(new SpinnerNumberModel(25, 1, 50, 1));
		imagePanel.add(imageSpinner);
		scanningPanel.add(imagePanel);

		JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		speedPanel.add(new JLabel("Rotation speed"));
		speedComboBox = new JComboBox<String>(new String[] {"05","01","02","03","04","05"});
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
		
		revisitButton = new JButton("Revisit");
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imageSpinner.setEnabled(false);
				speedComboBox.setEnabled(false);
				switchToPauseButton();
				startProgressBar();
			}
		});
		Controller.addExitButtonListener(exitButton,progressBar,this,mainFrame);
		add(buttonPanel,BorderLayout.SOUTH);
		//startProgressBar();
		setVisible(true);
		
		Controller.addCompleteButtonListener(completeButton,this, mainFrame, patient,mainFrame.getPatientTableModel());
	}

	

	private void startProgressBar(){ 
		isPaused = false;
		int noOfImages = (int) imageSpinner.getValue();
		int rotationSpeed = Integer.parseInt((String) speedComboBox.getSelectedItem());

		progressBarThread = new Thread(()->{

			for(int i=progressBar.getValue();i<=100;i++){
				if(isPaused)break;
				final int percent =i;
				//updates to the progressbar are made on edit

				SwingUtilities.invokeLater(()->{
					progressBar.setValue(percent);
					progressBar.setString(percent+ "%");
					if(percent==100)
					{
						progressBar.setForeground(Color.GREEN);
						progressBar.setString("Completed");
						buttonPanel.removeAll();

						buttonPanel.add(revisitButton);
                        revisitFunction();
						buttonPanel.add(completeButton);
						buttonPanel.revalidate();
						buttonPanel.repaint();
						//switchToRevisitAndRetake();
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

	private void switchToPauseButton(){
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

	private void switchToRevisitAndRetake(){
		buttonPanel.removeAll();
		
		revisitFunction();
		
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


		

	}
	
	
	private void resetProgressBar()
	{
		isPaused = false;
		progressBar.setValue(0);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setStringPainted(true);
		progressBar.setString("0%");
	}
	
	private void addStartAndCompleteButtons()
	{
		JButton startButton1 = new JButton("Start");
		startButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				switchToPauseButton();
				//progressBar.setForeground(Color.BLUE);
				startProgressBar();
			}
		});
		
		
		buttonPanel.add(startButton);
		buttonPanel.add(completeButton);
		buttonPanel.revalidate();
		buttonPanel.repaint();
		
	}
	
	private void revisitFunction()
	{
		revisitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imageSpinner.setEnabled(true);
				speedComboBox.setEnabled(true);
                buttonPanel.removeAll();
				resetProgressBar();
				addStartAndCompleteButtons();

			}
		});
	}
	

}