package gui;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.table.TableModel;
import javax.swing.text.html.HTML;

public class CustomPatientTable extends JTable{

	public CustomPatientTable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		ToolTipManager.sharedInstance().setInitialDelay(1500);
		ToolTipManager.sharedInstance().setDismissDelay(200000);
		ToolTipManager.sharedInstance().setReshowDelay(2000);
		int row = rowAtPoint(event.getPoint());
		int column = columnAtPoint(event.getPoint());
		if(getValueAt(row, column).toString().length()>20) {
			if(column == 2 || column == 7) {
				return "<html><body style = 'width:200px;' >" +  getValueAt(row, column).toString()+"</body></html>";
			}
		}
		return null;	
	}
}
