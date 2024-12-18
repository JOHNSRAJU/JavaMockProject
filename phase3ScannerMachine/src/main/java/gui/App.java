package gui;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App {
	public static void main(String[] args) {
		try {
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					UIManager.put("nimbusOrange",Color.GRAY);
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(()->new MainFrame()
				);
	}
}
