package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import model.PatientTableModel;

public class PatientTablePanel extends JPanel{
	private JTable patientTable;
	private PatientTableModel patientTableModel;
	private PatientSelectListener patientSelectListener;
	private TableColumn column; 
	public PatientTablePanel(PatientTableModel patientTableModel,PatientSelectListener patientSelectListener) {
		this.patientSelectListener = patientSelectListener;
		this.patientTableModel = patientTableModel;
		patientTable = new CustomPatientTable(patientTableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(patientTable),BorderLayout.CENTER);
		setBorder(new EmptyBorder(10,10,10,10));
		
		column = patientTable.getColumnModel().getColumn(0);
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		
		column = patientTable.getColumnModel().getColumn(1);
		column.setPreferredWidth(40);
		column.setMaxWidth(40);
		
		patientTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = patientTable.rowAtPoint(e.getPoint());
				patientSelectListener.patientSelected(patientTableModel.getData(row));
			}
			
		});
	}
	
}
