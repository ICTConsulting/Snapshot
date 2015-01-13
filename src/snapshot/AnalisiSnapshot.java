package snapshot;

import java.awt.EventQueue;
import javax.swing.JFrame;
import drawing.Finestra;

/**
 * it contains the main of the tool
 * @author Fontana Simone
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class AnalisiSnapshot {

	private static JFrame frmAnalisiReportQualys;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final ConfigParser config = new ConfigParser();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Finestra window = new Finestra(config.getCDEHost(), config.getExpEl(), getFrmAnalisiReportQualys());
					window.getFrmAnalisiReportQualys().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static JFrame getFrmAnalisiReportQualys() {
		return frmAnalisiReportQualys;
	}

	public void setFrmAnalisiReportQualys(JFrame frmAnalisiReportQualys) {
		AnalisiSnapshot.frmAnalisiReportQualys = frmAnalisiReportQualys;
	}

}
