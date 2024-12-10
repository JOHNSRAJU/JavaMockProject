package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PatientTablePanel extends JPanel{
	private JTable patientTable;
	private PatientTableModel patientTableModel;
	private PatientSelectListener patientSelectListener;
	public PatientTablePanel(PatientTableModel patientTableModel,PatientSelectListener patientSelectListener) {
		this.patientSelectListener = patientSelectListener;
		this.patientTableModel = patientTableModel;
		patientTable = new JTable(patientTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(patientTable),BorderLayout.CENTER);
		
		patientTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = patientTable.rowAtPoint(e.getPoint());
				patientSelectListener.patientSelected(PatientTablePanel.this.patientTableModel.getData(row));
				
			}
			
		});
	}
}
