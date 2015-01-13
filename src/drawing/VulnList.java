package drawing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;

import snapshot.Snapshot;
import snapshot.Vulnerability;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * a new windows displayed during the "search" function
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class VulnList extends JDialog {

	private static final long serialVersionUID = 8867728144118748183L;
	private final JPanel contentPanel = new JPanel();
	private java.util.List <String> vulnList = new ArrayList<String>();
	private java.util.List <Vulnerability> vulnObjList = new ArrayList<Vulnerability>();
	private String selectedVuln;
	private List list;
	private java.util.List<Snapshot> snapshotList;
	private Finestra chiamante;
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public VulnList(java.util.List<Snapshot> snapshotlst, Finestra finestra) {
		
		snapshotList = snapshotlst;
		chiamante = finestra;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2)-225, (int)(height/2)-125, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		list = new List();
		list.setBounds(34, 55, 367, 110);
		contentPanel.add(list);
		for(int i=0; i<snapshotList.size(); i++){
			for(int j=0; j<snapshotList.get(i).getHostList().size(); j++){
				for(int k=0; k<snapshotList.get(i).getHostList().get(j).getVulnList().size(); k++){
					if(!vulnList.contains(snapshotList.get(i).getHostList().get(j).getVulnList().get(k).getNumber())){
						vulnList.add(snapshotList.get(i).getHostList().get(j).getVulnList().get(k).getNumber());
						vulnObjList.add(snapshotList.get(i).getHostList().get(j).getVulnList().get(k));
					}
				}
			}
		}
		Collections.sort(vulnObjList, new Comparator<Vulnerability>() {
			@Override
			public int compare(Vulnerability vuln1, Vulnerability vuln2)
			{
				return  ((Integer)Integer.parseInt(vuln1.getNumber())).compareTo(Integer.parseInt(vuln2.getNumber()));
			}
		});
		list.select(0);

		for(int i=0; i<vulnObjList.size(); i++)
			list.add(vulnObjList.get(i).getNumber()+"      "+vulnObjList.get(i).getTitle()+"      "+vulnObjList.get(i).getCve());	
		
		JLabel lblListaDelleVulnerabilit = new JLabel("List of elements:");
		lblListaDelleVulnerabilit.setBounds(34, 10, 367, 15);
		contentPanel.add(lblListaDelleVulnerabilit);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedItem()!=null){
					setSelectedVuln(list.getSelectedItem().substring(0, list.getSelectedItem().indexOf(" ")));
					triggerThePlot();
				}else
					JOptionPane.showMessageDialog(null, "Select an element", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				list.removeAll();
				for(int i=0; i<vulnObjList.size(); i++){
					if((vulnObjList.get(i).getNumber().toLowerCase().contains(textField.getText().toLowerCase()))||(vulnObjList.get(i).getTitle().toLowerCase().contains(textField.getText().toLowerCase()))||(vulnObjList.get(i).getCve().toLowerCase().contains(textField.getText().toLowerCase())))
						list.add(vulnObjList.get(i).getNumber()+"      "+vulnObjList.get(i).getTitle()+"      "+vulnObjList.get(i).getCve());
				}
				list.select(0);
			}

		});
		textField.setBounds(34, 30, 367, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						if(list.getSelectedItem()!=null){
							setSelectedVuln(list.getSelectedItem().substring(0, list.getSelectedItem().indexOf(" ")));
							triggerThePlot();
						}else
							JOptionPane.showMessageDialog(null, "Select an element", "Error", JOptionPane.ERROR_MESSAGE);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setSelectedVuln("");
						chiamante.visualizza();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * methods called when the vuln to search is selected
	 */
	public void triggerThePlot(){
		// TODO Auto-generated method stub
		chiamante.setNumVulnToSearch(getSelectedVuln());
		chiamante.setNomeStatoAttuale();
		for(int k=0; k<snapshotList.size(); k++){
			chiamante.drawVulnerabilityPlotSearchEdition(snapshotList.get(k), getSelectedVuln(), 0.5);
		}
		chiamante.drawRangeHostPlot(chiamante.getCDECheck().isSelected(), -1);
		chiamante.adjustPlot();
		for(int i=0; i<vulnObjList.size(); i++){
			if(vulnObjList.get(i).getNumber().equals(this.getSelectedVuln())){
				chiamante.lblVulnerabilities.setText("Vulnerability: " + vulnObjList.get(i).getTitle());
				break;
			}
		}
		
		dispose();
	}

	public String getSelectedVuln() {
		return selectedVuln;
	}

	public void setSelectedVuln(String selectedVuln) {
		this.selectedVuln = selectedVuln;
	}
}
