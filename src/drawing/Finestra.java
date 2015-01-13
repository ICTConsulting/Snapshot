package drawing;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import snapshot.ExposedElement;
import snapshot.Host;
import snapshot.ReadReport;
import snapshot.Snapshot;
import snapshot.StatoAttuale;

import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * it handles the data to display
 * @author Fontana Simone
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class Finestra {

	private JFrame frmAnalisiReportQualys;
	private List<Snapshot> snapshotList = new ArrayList <Snapshot>();
	private ReadReport read;
	private List<String> CDEHost = new ArrayList<String>();
	private List<ExposedElement> ExposedList = new ArrayList<ExposedElement>();
	private int maxNumVuln = 0;
	private int maxNumberOfHost = 0;
	private int numberOfSnap = 0;
	private int altezzaLabelIPmin = 0;
	private int altezzaLabelIPmax = 0;
	private int idRifInteger = 0;
	private int normalizedRatio = 10;
	private int ratioRangeHost = 1;
	private JCheckBox PCICheck;
	private JCheckBox CDECheck;
	private JCheckBox cveCheck;
	private JCheckBox vulnCheck;
	private JCheckBox practCheck;
	private JCheckBox servCheck;
	private JCheckBox infoCheck;
	private JCheckBox resetCheck;
	private JCheckBox expCheck;
	private java.awt.List list;
	private JLabel exitList;
	public JLabel lblVulnerabilities;
	private List<JLabel> numVulnLabelList = new ArrayList <JLabel>();
	private List<JLabel> dataSnapList = new ArrayList <JLabel>();
	private List<JLabel> numHostTested = new ArrayList <JLabel>();
	private List<DrawPlot> plotList = new ArrayList <DrawPlot>();
	private List<DrawSignature> sinatureList = new ArrayList <DrawSignature>();
	private JLabel label_3 = new JLabel("IP min");
	private JLabel label_46 = new JLabel("IP max");
	private JButton btnShow;
	private JButton btnAggiungiSnapshot;
	private String numVulnToSearch = "";
	private StatoAttuale status;
	private Finestra finestra;
	private int contaVuln = 0;
	private int contaPract = 0;
	private int contaServ = 0;
	private int contaInf = 0;
	private JButton btnVulnReference = new JButton("Benchmark");
	private JButton btnVulnSearch = new JButton("Search");
	private JButton btnReport;
	private JButton btnEliminaSnapshot;


	/**
	 * It crates the principal window of the tool
	 */
	public Finestra(List<String> CDElist, List<ExposedElement> ExposedList,JFrame frm) {
		this.setFrmAnalisiReportQualys(frm);
		this.setCDEHost(CDElist);
		this.setExposedList(ExposedList);
		finestra = this;
		initialize();
	}

	/**
	 * Initializes the components 
	 */
	private void initialize() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\icon.png"));
		setFrmAnalisiReportQualys(frame);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		getFrmAnalisiReportQualys().setTitle("Analisi Report Qualys");
		getFrmAnalisiReportQualys().setBounds((int)(width/2) - 450, (int)(height/2) - 350, 900, 650);
		getFrmAnalisiReportQualys().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrmAnalisiReportQualys().setResizable(false);
		getFrmAnalisiReportQualys().getContentPane().setLayout(null);

		PCICheck = new JCheckBox("PCI_FLAG");
		PCICheck.setBounds(5, 50, 90, 20);
		PCICheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(PCICheck);

		cveCheck = new JCheckBox("Detailed CVE");
		cveCheck.setBounds(5, 130, 110, 20);
		cveCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(cveCheck);
		cveCheck.setSelected(false);

		vulnCheck = new JCheckBox("VULNS");
		vulnCheck.setBounds(5, 170, 65, 20);
		vulnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(vulnCheck);
		vulnCheck.setSelected(true);

		practCheck = new JCheckBox("PRACTICES");
		practCheck.setBounds(5, 190, 90, 20);
		practCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(practCheck);

		servCheck = new JCheckBox("SERVICES");
		servCheck.setBounds(5, 210, 90, 20);
		servCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(servCheck);

		infoCheck = new JCheckBox("INFOS");
		infoCheck.setBounds(5, 230, 90, 20);
		infoCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(infoCheck);

		CDECheck = new JCheckBox("CDE");
		CDECheck.setBounds(5, 321, 50, 20);
		CDECheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visualizza();
			}
		});
		frame.getContentPane().add(CDECheck);
		if(this.getCDEHost().isEmpty())
			CDECheck.setEnabled(false);

		expCheck = new JCheckBox("Exposed only");
		expCheck.setBounds(5, 288, 100, 20);
		expCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(expCheck.isSelected()){
					btnVulnReference.setEnabled(false);
					btnVulnSearch.setEnabled(false);
				}else{
					btnVulnReference.setEnabled(true);
					btnVulnSearch.setEnabled(true);
				}
				visualizza();
			}
		});
		frame.getContentPane().add(expCheck);
		if(this.getExposedList().isEmpty())
			expCheck.setEnabled(false);

		setResetCheck(new JCheckBox("filter"));
		resetCheck.setBounds(5, 580, 65, 25);
		resetCheck.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				PCICheck.setEnabled(true);
				CDECheck.setEnabled(true);
				cveCheck.setEnabled(true);
				vulnCheck.setEnabled(true);
				practCheck.setEnabled(true);
				servCheck.setEnabled(true);
				infoCheck.setEnabled(true);
				expCheck.setEnabled(true);
				lblVulnerabilities.setText("Vulnerability");
				idRifInteger = 0;
				numVulnToSearch = "";
				PCICheck.setSelected(false);
				CDECheck.setSelected(false);
				vulnCheck.setSelected(true);
				practCheck.setSelected(false);
				servCheck.setSelected(false);
				infoCheck.setSelected(false);
				status = new StatoAttuale();
				status.setSnapshotList(snapshotList);
				setNomeStatoAttuale();
				visualizza();
				resetCheck.setVisible(false);
			}
		});
		frame.getContentPane().add(resetCheck);
		resetCheck.setVisible(false);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images\\legend2.png"));
		lblNewLabel.setBounds(740, 20, 150, 160);
		frame.getContentPane().add(lblNewLabel);

		setExitList(new JLabel("X"));
		exitList.setBounds(875, 340, 10, 10);
		frame.getContentPane().add(exitList);
		exitList.setVisible(false);
		exitList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				list.setVisible(false);
				exitList.setVisible(false);
			}
		});
		list = new java.awt.List();
		list.setBounds(735, 350, 150, 150);
		frame.getContentPane().add(list);
		list.setVisible(false);

		btnAggiungiSnapshot = new JButton("Add Snapshot");
		btnAggiungiSnapshot.setBounds(125, 580, 130, 25);
		btnAggiungiSnapshot.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {

				boolean check = false;
				int count = 1;
				String current = System.getProperty("user.dir");
				File f = new File(current);
				ArrayList<String> folderNames = new ArrayList<String>(Arrays.asList(f.list()));
				setNumVulnToSearch("");
				lblVulnerabilities.setText("Vulnerability");

				while(check == false){
					if(folderNames.contains(Integer.toString(count))){
						f = new File(current+"//"+Integer.toString(count));
						ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
						if((names.contains("body.txt"))&&(names.contains("header.txt")))
						{
							count++;
						}
						else if(names.isEmpty()){
							JOptionPane.showMessageDialog(null, "Folder "+ count +" contains no report", "Error", JOptionPane.ERROR_MESSAGE);
							check = true;
						}else{
							try {
								File batFile = new File(current+"//"+Integer.toString(count)+"//ScanFilter2tsv.bat");
								FileWriter fw = new FileWriter(batFile);
								for(int i=0; i<names.size(); i++){
									fw.write(current+"\\parser\\msxsl.exe ");  
									fw.write("\""+current+"\\"+Integer.toString(count)+"\\"+names.get(i)+"\" "+current+"\\parser\\ScanFilter2tsv_header.xsl -xe -o "+current+"\\"+Integer.toString(count)+"\\output"+i+".txt\r\n");
									fw.write(current+"\\parser\\msxsl.exe "); 
									fw.write("\""+current+"\\"+Integer.toString(count)+"\\"+names.get(i)+"\" "+current+"\\parser\\ScanFilter2tsv_body.xsl -xe -o "+current+"\\"+Integer.toString(count)+"\\report"+i+".txt\r\n");
								}
								fw.write("copy "+current+"\\"+Integer.toString(count)+"\\output*.txt "+current+"\\"+Integer.toString(count)+"\\header.txt /A\r\n");
								fw.write("del "+current+"\\"+Integer.toString(count)+"\\output*.txt\r\n");
								fw.write("copy "+current+"\\"+Integer.toString(count)+"\\report*.txt "+current+"\\"+Integer.toString(count)+"\\body.txt /A\r\n");
								fw.write("del "+current+"\\"+Integer.toString(count)+"\\report*.txt\r\n");
								fw.write("exit");
								fw.flush();
								fw.close();
								setNumberOfSnap(0);
								Runtime.getRuntime().exec( "cmd /c start "+current+"//"+Integer.toString(count)+"//ScanFilter2tsv.bat");
								check = true;
								btnAggiungiSnapshot.setVisible(false);
								btnShow.setVisible(true);
							}
							catch(IOException e) { 
								e.printStackTrace();
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Folder "+ count +" not found", "Error", JOptionPane.ERROR_MESSAGE);
						count = count - 1;
						check = true;
					}
				}
			}
		});
		getFrmAnalisiReportQualys().getContentPane().add(btnAggiungiSnapshot);

		btnEliminaSnapshot = new JButton("Del Snapshot");
		btnEliminaSnapshot.setBounds(280, 580, 130, 25);
		btnEliminaSnapshot.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				try{
					String idToDelete = JOptionPane.showInputDialog("Insert the snapshot ID to delete:");
					int idToDeleteInteger = Integer.parseInt(idToDelete);
					setMaxNumVuln(0);
					setNumVulnToSearch("");
					status = new StatoAttuale();
					status.setSnapshotList(snapshotList);
					setNomeStatoAttuale();
					lblVulnerabilities.setText("Vulnerability");

					if((idToDeleteInteger <= snapshotList.size())&&(idToDeleteInteger > 0)){
						System.out.println(idToDeleteInteger-1);

						for(int i=0; i<snapshotList.get(idToDeleteInteger-1).getDataSnapshot().size();i++){
							snapshotList.get(idToDeleteInteger-1).getDataSnapshot().get(i).getPosizioneHostRange().setVisible(false);
						}

						for(int j=0;j<snapshotList.get(idToDeleteInteger-1).getHostList().size(); j++){
							snapshotList.get(idToDeleteInteger-1).getHostList().get(j).getPosizioneHostRange().setVisible(false);
						}	
						snapshotList.remove(idToDeleteInteger-1);
						puliziaGrafici();
						for(int k=0; k<snapshotList.size(); k++){
							drawVulnerabilityPlot(snapshotList.get(k), PCICheck.isSelected(), CDECheck.isSelected(), -1, normalizedRatio, status, expCheck.isSelected());
						}
						drawRangeHostPlot(CDECheck.isSelected(),-1);
						adjustPlot();
					}else{
						JOptionPane.showMessageDialog(null, "Snapshot "+ idToDelete +" not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Insert a correct integer number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getFrmAnalisiReportQualys().getContentPane().add(btnEliminaSnapshot);


		btnVulnReference.setBounds(435, 580, 130, 25);
		btnVulnReference.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				try{
					String idRiferimento = JOptionPane.showInputDialog("Insert the ID of benchmark snapshot:");
					idRifInteger = Integer.parseInt(idRiferimento);
					setMaxNumVuln(0);
					setNumVulnToSearch("");
					maxNumberOfHost = 0;
					lblVulnerabilities.setText("Vulnerability");

					resetCheck.setVisible(true);
					resetCheck.setSelected(true);
					status = new StatoAttuale();
					status.setSnapshotList(snapshotList);
					setNomeStatoAttuale();
					if((idRifInteger <= snapshotList.size())&&(idRifInteger > 0)){
						puliziaGrafici();

						for(int k=0; k<snapshotList.size(); k++){
							drawVulnerabilityPlot(snapshotList.get(k), PCICheck.isSelected(), CDECheck.isSelected(), idRifInteger-1, normalizedRatio, status, expCheck.isSelected());
						}
						drawRangeHostPlot(CDECheck.isSelected(), idRifInteger-1);
						adjustPlot();
					}else{
						JOptionPane.showMessageDialog(null, "Snapshot "+ idRifInteger +" not found", "Error", JOptionPane.ERROR_MESSAGE);
					}
					PCICheck.setEnabled(false);
					CDECheck.setEnabled(false);
					cveCheck.setEnabled(false);
					vulnCheck.setEnabled(false);
					practCheck.setEnabled(false);
					servCheck.setEnabled(false);
					infoCheck.setEnabled(false);
					expCheck.setEnabled(false);
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Insert a correct integer number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		getFrmAnalisiReportQualys().getContentPane().add(btnVulnReference);

		btnVulnSearch.setBounds(590, 580, 130, 25);
		btnVulnSearch.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				CDECheck.setSelected(false);
				maxNumberOfHost = 0;
				status = new StatoAttuale();
				status.setSnapshotList(snapshotList);
				resetCheck.setVisible(true);
				resetCheck.setSelected(true);
				VulnList dialog = null;
				lblVulnerabilities.setText("Vulnerability");

				try {
					dialog = new VulnList(snapshotList, finestra);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setIconImage(Toolkit.getDefaultToolkit().getImage("images\\icon.png"));
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent we) {
							resetCheck.setVisible(false);
							visualizza();
						}
					} );
				} catch (Exception e) {
					e.printStackTrace();
				}

				maxNumVuln = 0;
				puliziaGrafici();
				PCICheck.setEnabled(false);
				CDECheck.setEnabled(false);
				cveCheck.setEnabled(false);
				vulnCheck.setEnabled(false);
				practCheck.setEnabled(false);
				servCheck.setEnabled(false);
				infoCheck.setEnabled(false);
				expCheck.setEnabled(false);
			}
		});
		getFrmAnalisiReportQualys().getContentPane().add(btnVulnSearch);

		btnReport = new JButton("Report");
		btnReport.setBounds(745, 580, 130, 25);
		btnReport.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				if(status == null)
					JOptionPane.showMessageDialog(null, "Load the snapshots first", "Error", JOptionPane.ERROR_MESSAGE);
				else if(status.scriviReport() == false){
					JOptionPane.showMessageDialog(null, "Error during generation", "Error", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "Report correctly generated", "OK", JOptionPane.PLAIN_MESSAGE);
			}

		});
		getFrmAnalisiReportQualys().getContentPane().add(btnReport);

		btnShow = new JButton("Display");
		btnShow.setBounds(125, 580, 130, 25);
		btnShow.setVisible(false);
		btnShow.addActionListener(new ActionListener() {	

			public void actionPerformed(ActionEvent arg0) {
				lblVulnerabilities.setText("Vulnerability");
				visualizza();
				btnAggiungiSnapshot.setVisible(true);
				btnShow.setVisible(false);
			}

		});
		getFrmAnalisiReportQualys().getContentPane().add(btnShow);

		AsseX asseX = new AsseX();
		asseX.setBounds(120, 245, 605, 10);
		getFrmAnalisiReportQualys().getContentPane().add(asseX);
		AsseY asseY = new AsseY();
		asseY.setBounds(120, 50, 10, 201);
		getFrmAnalisiReportQualys().getContentPane().add(asseY);

		AsseX asseX1 = new AsseX();
		asseX1.setBounds(120, 545, 605, 10);
		getFrmAnalisiReportQualys().getContentPane().add(asseX1);
		AsseY asseY1 = new AsseY();
		asseY1.setBounds(120, 350, 10, 201);
		getFrmAnalisiReportQualys().getContentPane().add(asseY1);

		lblVulnerabilities = new JLabel("Vulnerability");
		lblVulnerabilities.setBounds(90, 19, 600, 14);
		getFrmAnalisiReportQualys().getContentPane().add(lblVulnerabilities);

		JLabel lblIpAddress = new JLabel("IP Address");
		lblIpAddress.setBounds(90, 320, 79, 20);
		getFrmAnalisiReportQualys().getContentPane().add(lblIpAddress);

		JLabel label = new JLabel("t");
		label.setBounds(740, 541, 4, 14);
		getFrmAnalisiReportQualys().getContentPane().add(label);

		JLabel lblT = new JLabel("t");
		lblT.setBounds(740, 240, 4, 14);
		getFrmAnalisiReportQualys().getContentPane().add(lblT);

		this.visualizza();
	}

	/**
	 * loads the data from the files (body.txt and header.txt) placed in relative folders, 
	 * then it calls method for sketching the plots
	 */
	public void visualizza() {
		// TODO Auto-generated method stub
		boolean check = false;
		lblVulnerabilities.setText("Vulnerability");
		int count = 1;
		String current = System.getProperty("user.dir");
		File f = new File(current);
		ArrayList<String> folderNames = new ArrayList<String>(Arrays.asList(f.list()));
		setNumVulnToSearch("");
		setMaxNumVuln(0);
		maxNumberOfHost = 0;
		status = new StatoAttuale();
		setNomeStatoAttuale();
		puliziaGrafici();

		if(numberOfSnap == 0){			
			snapshotList = new ArrayList <Snapshot>();
			while(check == false){
				if(folderNames.contains(Integer.toString(count))){
					f = new File(current+"//"+Integer.toString(count));
					ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
					if((names.contains("body.txt"))&&(names.contains("header.txt"))){
						read = new ReadReport(count);
						snapshotList.add(read.getSnap());
						count++;
					}else{
						check = true;
						numberOfSnap = count - 1;
					}
				}else{
					check = true;
					numberOfSnap = count - 1;
				}
			}
			exposedAssociation();
		} 

		if(snapshotList.size() == 0){
			btnVulnReference.setEnabled(false);
			btnReport.setEnabled(false);
			btnVulnSearch.setEnabled(false);
			btnEliminaSnapshot.setEnabled(false);
		}else if(snapshotList.size() == 1){
			btnVulnReference.setEnabled(false);
			btnReport.setEnabled(true);
			btnVulnSearch.setEnabled(true);
			btnEliminaSnapshot.setEnabled(true);
		}else{
			btnVulnReference.setEnabled(true);
			btnReport.setEnabled(true);
			btnVulnSearch.setEnabled(true);
			btnEliminaSnapshot.setEnabled(true);
		}

		expCheck.setEnabled(false);
		for(int i=0; i<this.getExposedList().size(); i++){
			if(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())<=snapshotList.size()){
				expCheck.setEnabled(true);
				break;
			}
		}

		for(int k=0; k<snapshotList.size(); k++){
			drawVulnerabilityPlot(snapshotList.get(k), PCICheck.isSelected(), CDECheck.isSelected(), -1, normalizedRatio, status, expCheck.isSelected());
		}
		drawRangeHostPlot(CDECheck.isSelected(), -1);
		status.setSnapshotList(snapshotList);
		adjustPlot();
	}

	private void exposedAssociation() {
		// TODO Auto-generated method stub

		for(int i=0; i<this.getExposedList().size(); i++){
			if((Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1)<this.snapshotList.size()){
				snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).setExposed(true);
				for(int j=0; j<snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().size(); j++){
					for(int k=0; k<snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().size(); k++){
						for(int m=0; m<snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getCveList().size(); m++){
							if(((snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getIPAddress().equals(this.getExposedList().get(i).getIPAddress()))||(this.getExposedList().get(i).getIPAddress().equals("*")))
									&&((snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getPortNumber().equals(this.getExposedList().get(i).getPortNumber()))||(this.getExposedList().get(i).getPortNumber().equals("*")))
									&&((snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getNumber().equals(this.getExposedList().get(i).getNumber()))||(this.getExposedList().get(i).getNumber().equals("*")))
									&&((snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getCveList().get(m).getID().equals(this.getExposedList().get(i).getCve()))||(this.getExposedList().get(i).getNumber().equals("*")))
									){
								snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).setExposed(true);
								snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getCveList().get(m).setExposed(true);
								//System.out.println(snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getIPAddress()+";"+snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getPortNumber()+";"+snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getNumber()+" -> "+snapshotList.get(Integer.parseInt(this.getExposedList().get(i).getSnapshotID())-1).getHostList().get(j).getVulnList().get(k).getCveList().size());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * called after each visualization. It adjusts the vulnerability bars wrt the max number of vulns 
	 */
	public void adjustPlot() {
		// TODO Auto-generated method stub
		status = new StatoAttuale();
		status.setSnapshotList(snapshotList);
		setNomeStatoAttuale();
		puliziaGrafici();
		if(numVulnToSearch == ""){
			for(int k=0; k<snapshotList.size(); k++){
				drawVulnerabilityPlot(snapshotList.get(k), PCICheck.isSelected(), CDECheck.isSelected(), idRifInteger-1, (double)maxNumVuln/200, status, expCheck.isSelected());
			}
			drawRangeHostPlot(CDECheck.isSelected(), idRifInteger-1);
			idRifInteger = 0;
		}else{
			for(int k=0; k<snapshotList.size(); k++){
				drawVulnerabilityPlotSearchEdition(snapshotList.get(k), numVulnToSearch, (double)maxNumVuln/200);
			}
			maxNumberOfHost = 0;
			drawRangeHostPlot(CDECheck.isSelected(), idRifInteger-1);
			numVulnToSearch = "";
		}
	}

	/**
	 * it draws lines wherein each point represents a host analyzed by Qualys
	 * @param onlyCDE: restricts the scope to the one defined in the config.xml file
	 * @param idRif: represents the ID number of the snapshot selected as the benchmark, -1 otherwise
	 */
	public void drawRangeHostPlot(boolean onlyCDE, int idRif) {
		// TODO Auto-generated method stub
		int altezza = 0;
		List<Host> totalHostList = new ArrayList<Host>();
		int originalSizeHost = 0;

		for(int i=0; i<snapshotList.size();i++){
			System.out.println("number of hosts in "+(i+1)+": "+snapshotList.get(i).getHostList().size());
			if(snapshotList.get(i).getHostList().size()>originalSizeHost)
				originalSizeHost = snapshotList.get(i).getHostList().size();
			if(idRif == -1){
				for(int j=0; j<snapshotList.get(i).getHostList().size(); j++){
					totalHostList.add(snapshotList.get(i).getHostList().get(j));
				}
			}else{
				for(int j=0; j<snapshotList.get(i).getHostList().size(); j++){
					for(int k=0; k<snapshotList.get(idRif).getHostList().size(); k++){
						if(snapshotList.get(idRif).getHostList().get(k).getNum() == snapshotList.get(i).getHostList().get(j).getNum()){
							totalHostList.add(snapshotList.get(i).getHostList().get(j));
							break;
						}
					}
				}
			}
		}

		/**
		 * hides hosts and labels showed in the previous visualization
		 */
		for(int i=0; i<snapshotList.size();i++){
			for(int j=0; j<snapshotList.get(i).getHostList().size(); j++){
				if(snapshotList.get(i).getHostList().get(j).getPosizioneHostRange()!=null)
					snapshotList.get(i).getHostList().get(j).getPosizioneHostRange().setVisible(false);
			}
		}
		setAltezzaLabelIPmin(0);
		setAltezzaLabelIPmax(0);

		Collections.sort(totalHostList, new Comparator<Host>() {
			@Override
			public int compare(Host host1, Host host2)
			{
				return  Long.compare(host1.getNum(), host2.getNum());//	.compareTo(Long.toString(host2.getNum()));
			}
		});
		System.out.println("total host size: "+totalHostList.size());

		if(numVulnToSearch == "")
			ratioRangeHost = 1;
		else 
			maxNumberOfHost = originalSizeHost;

		if(maxNumberOfHost != 0)
			ratioRangeHost = (int)Math.floor(200/(int)maxNumberOfHost);

		for(int j=0; j<totalHostList.size(); j++){
			for(int k=0; k<snapshotList.size(); k++){
				for(int i=0; i<snapshotList.get(k).getDataSnapshot().size(); i++){
					if((snapshotList.get(k).getDataSnapshot().get(i).getPosizioneHostRange() == null)||(snapshotList.get(k).getDataSnapshot().get(i).getPosizioneHostRange().isVisible() == false))
						inizializzaAsse(k, i);
					if(snapshotList.get(k).getDataSnapshot().get(i).equals(totalHostList.get(j).getDataAnalisi())){						
						if(onlyCDE == false){
							altezza = drawHost(altezza, totalHostList, j, k, i, ratioRangeHost);
						}else{
							if(this.getCDEHost().contains(totalHostList.get(j).getIPAddress())){
								altezza = drawHost(altezza, totalHostList, j, k, i, ratioRangeHost);
							}
						}	
					}
				}
			}
		}

		/**
		 * hides those hosts which have not the searched vulnerability
		 */
		for(int j=0; j<totalHostList.size(); j++){
			if(numVulnToSearch != ""){
				boolean isPresent = false;
				for(int m = 0; m<totalHostList.get(j).getVulnList().size(); m++){
					isPresent = false;
					if(totalHostList.get(j).getVulnList().get(m).getNumber().equals(numVulnToSearch)){
						isPresent = true;
						break;
					}
				}
				if(isPresent == false)
					totalHostList.get(j).getPosizioneHostRange().setVisible(false);
			}
		}

		/**
		 * updates the labels with the number of host displayed
		 */
		status.setNumberOfTestedHost(new ArrayList<Integer>());
		for(int k=0; k<snapshotList.size(); k++){
			int numHost = 0;
			for(int j=0; j<snapshotList.get(k).getHostList().size(); j++){
				if(snapshotList.get(k).getHostList().get(j).getPosizioneHostRange().isVisible())
					numHost++;
			}
			status.getNumberOfTestedHost().add(numHost);
			JLabel numHostLabel = new JLabel(Integer.toString(numHost));
			numHostLabel.setHorizontalAlignment(SwingConstants.CENTER);
			numHostLabel.setBounds((snapshotList.get(k).getID()*100) + 63, 335, 30, 15);
			getFrmAnalisiReportQualys().getContentPane().add(numHostLabel);
			numHostTested.add(numHostLabel);
			if(numHost>maxNumberOfHost)
				maxNumberOfHost = numHost;
		}

		/**
		 * sets 2 labels with the min and max IP address found 
		 */
		if(getAltezzaLabelIPmax() == 0)
			label_46.setVisible(false);
		if(getAltezzaLabelIPmin() == 0)
			label_3.setVisible(false);
		label_3.setBounds(35, 550 - getAltezzaLabelIPmin(), 100, 10);
		getFrmAnalisiReportQualys().getContentPane().add(label_3);
		label_46.setBounds(35, 550 - getAltezzaLabelIPmax(), 100, 10);
		getFrmAnalisiReportQualys().getContentPane().add(label_46);

		drawSignature();
	}

	/**
	 * it inserts the red point below the line. Once clicked, it shows some info about the analysis
	 * @param k: represents the ID of the snapshot
	 * @param i: represents the ID of the single analysis
	 */
	private void inizializzaAsse(int k, int i) {
		// TODO Auto-generated method stub
		DrawHostRange plotRange = new DrawHostRange();
		plotRange.setAnalisi(true);
		getFrmAnalisiReportQualys().getContentPane().add(plotRange);
		plotRange.setSize(10, 8);
		plotRange.setLocation(50 + (snapshotList.get(k).getID()*100 + (i*10)), 555);
		snapshotList.get(k).getDataSnapshot().get(i).setPosizioneHostRange(plotRange);
		plotRange.setVisible(true);
		plotRange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//System.out.println(arg0.getComponent().getX());
				list.setVisible(true);
				exitList.setVisible(true);
				int idSelezione = (int) Math.floor((arg0.getComponent().getX()-150)/100);
				int idAnalisi = (int) Math.floor((arg0.getComponent().getX()-150 - 100*idSelezione)/10);
				//System.out.println(idSelezione);
				list.removeAll();
				list.add("Date: " + snapshotList.get(idSelezione).getDataSnapshot().get(idAnalisi).getGiorno()+"/"+snapshotList.get(idSelezione).getDataSnapshot().get(idAnalisi).getMese()+"/"+snapshotList.get(idSelezione).getDataSnapshot().get(idAnalisi).getAnno()+" at "+snapshotList.get(idSelezione).getDataSnapshot().get(idAnalisi).getOra()+":"+snapshotList.get(idSelezione).getDataSnapshot().get(idAnalisi).getMinuto());
				list.add("Title: " + snapshotList.get(idSelezione).getOption().getTitle().get(idAnalisi));
				list.add("Duration: " + snapshotList.get(idSelezione).getOption().getDurata().get(idAnalisi));
				list.add(Integer.toString(snapshotList.get(idSelezione).getOption().getAliveHost().get(idAnalisi))+"/"+Integer.toString(snapshotList.get(idSelezione).getOption().getTotalHost().get(idAnalisi))+" responding hosts");
				list.add(snapshotList.get(idSelezione).getOption().getSignatureList().get(idAnalisi));
				list.add(snapshotList.get(idSelezione).getOption().getScannerList().get(idAnalisi));
				list.add("Status: "+snapshotList.get(idSelezione).getOption().getStatus().get(idAnalisi));
			}
		});
	}

	/**
	 * draws the single host as a dot
	 * @param ratio: is the height of the dot. his value depends on the max number of hosts shown
	 * @param altezza: represents the distance from the x axes for that host
	 */
	private int drawHost(int altezza, List<Host> totalHostList, int j, int k, int i, int ratio) {
		// TODO Auto-generated method stub
		DrawHostRange plotRange = new DrawHostRange();
		plotRange.setRatio(ratio);

		/**
		 * 'altezza' increases whenever the host's IP address is changed wrt the previous
		 */
		if(!((altezza != 0)&&(totalHostList.get(j).getNum() == totalHostList.get(j-1).getNum()))){
			altezza = altezza + ratio;
		}
		totalHostList.get(j).setPosizioneHostRange(plotRange);

		plotRange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int conta = 0;
				list.setVisible(true);
				exitList.setVisible(true);
				int idSelezione = (int) Math.floor((arg0.getComponent().getX()-150)/100);
				int idHost =(int) Math.floor((arg0.getComponent().getY()));
				for(int i=0; i<snapshotList.get(idSelezione).getHostList().size(); i++){
					if(idHost == snapshotList.get(idSelezione).getHostList().get(i).getPosizioneHostRange().getY()){
						idHost = i;
					}
				}
				list.removeAll();
				list.add("Address: "+snapshotList.get(idSelezione).getHostList().get(idHost).getIPAddress());
				list.add("Server Name: "+snapshotList.get(idSelezione).getHostList().get(idHost).getName());
				list.add("OS: "+snapshotList.get(idSelezione).getHostList().get(idHost).getOs());
				list.add("");
				list.add("Vulns:");
				for(int i=0; i<snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().size(); i++)
					if(snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getType() == 1){
						list.add((conta+1)+")  "+snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getTitle());
						conta++;
					}
				conta = 0;
				list.add("");
				list.add("Practices:");
				for(int i=0; i<snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().size(); i++)
					if(snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getType() == 2){
						list.add((conta+1)+")  "+snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getTitle());
						conta++;
					}
				conta = 0;
				list.add("");
				list.add("Services:");
				for(int i=0; i<snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().size(); i++)
					if(snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getType() == 3){
						list.add((conta+1)+")  "+snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getTitle());
						conta++;
					}
				conta = 0;
				list.add("");
				list.add("Infos:");
				for(int i=0; i<snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().size(); i++)
					if(snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getType() == 4){
						list.add((conta+1)+")  "+snapshotList.get(idSelezione).getHostList().get(idHost).getVulnList().get(i).getTitle());
						conta++;
					}
			}
		});
		totalHostList.get(j).getPosizioneHostRange().setSize(10, ratio + 2);
		totalHostList.get(j).getPosizioneHostRange().setLocation(50 + (snapshotList.get(k).getID()*100 + (i*10)), 550 - altezza - 1);
		totalHostList.get(j).getPosizioneHostRange().setVisible(true);
		totalHostList.get(j).getPosizioneHostRange().setToolTipText(totalHostList.get(j).getIPAddress());
		getFrmAnalisiReportQualys().getContentPane().add(totalHostList.get(j).getPosizioneHostRange());

		if(j == 0){
			setAltezzaLabelIPmin(altezza + 5);
			label_3.setText(totalHostList.get(j).getIPAddress());
		}
		else if(j == (totalHostList.size() - 1)){
			setAltezzaLabelIPmax(altezza + 5);
			label_46.setText(totalHostList.get(j).getIPAddress());
		}

		return altezza;
	}

	/**
	 * it draws an histogram where each bar represents the amount of vulnerabilities found for a given snapshot
	 * @param onlyPCI: reduces the scope for those vulnerabilities flagged as PCI relevant
	 * @param onlyCDE: reduces the scope only for those hosts defined in the config.xml file
	 * @param idRif: equals to the ID of the benchmark snapshot, -1 otherwise
	 * @param adjust: represents the normalized ratio for the bars' heights
	 */
	private void drawVulnerabilityPlot(Snapshot snapshot, boolean onlyPCI, boolean onlyCDE, int idRif, double adjust, StatoAttuale status, boolean onlyExposed) {
		// TODO Auto-generated method stub
		DrawPlot plot = new DrawPlot();
		contaVuln = 0;
		contaPract = 0;
		contaServ = 0;
		contaInf = 0;

		plot.setAdjust(adjust);
		for(int i=0; i < snapshot.getHostList().size(); i++){
			for(int j=0; j < snapshot.getHostList().get(i).getVulnList().size(); j++){
				if(onlyPCI == false && onlyCDE == false){
					elementCleaner(idRif, snapshot, plot, i, j);
				}else if(onlyPCI == true && onlyCDE == false){
					if(snapshot.getHostList().get(i).getVulnList().get(j).isPci()){
						elementCleaner(idRif, snapshot, plot, i, j);
					}
				}else if(onlyPCI == false && onlyCDE == true){
					if(this.getCDEHost().contains(snapshot.getHostList().get(i).getIPAddress())){
						elementCleaner(idRif, snapshot, plot, i, j);
					}
				}else if(onlyPCI == true && onlyCDE == true){
					if(this.getCDEHost().contains(snapshot.getHostList().get(i).getIPAddress())){
						if(snapshot.getHostList().get(i).getVulnList().get(j).isPci()){
							elementCleaner(idRif, snapshot, plot, i, j);		
						}
					}
				}
			}
		}

		plot.sommaAltezze();
		getFrmAnalisiReportQualys().getContentPane().add(plot);
		plot.setSize(100, 250);
		plot.setLocation((snapshot.getID()*100), 0);
		plot.setVisible(true);
		plotList.add(plot);

		/**
		 * keeps track of the snapshot with the max amount of elements
		 */
		if((contaVuln+contaPract+contaServ+contaInf)>getMaxNumVuln())
			setMaxNumVuln(contaVuln + contaPract + contaServ + contaInf);

		updateLabelValuesAfterDrawnABar(plot, snapshot);
		statusUpdate(plot);
	}

	/**
	 * switches each element to the respective drawer 
	 */
	private void elementCleaner(int idRif, Snapshot snapshot, DrawPlot plot, int i, int j) {
		// TODO Auto-generated method stub
		if(idRif == -1){
			if(cveCheck.isSelected()){
				if(expCheck.isSelected()){
					for(int k=0; k<snapshot.getHostList().get(i).getVulnList().get(j).getCveList().size(); k++){
						if(snapshot.getHostList().get(i).getVulnList().get(j).getCveList().get(k).isExposed()){
							plot.setGreyEdition(false);
							drawRectNormalEdition(i, j, plot, snapshot);		
						}else{
							if(snapshot.isExposed()==false){
								plot.setGreyEdition(true);
								drawRectNormalEdition(i, j, plot, snapshot);
							}
						}
					}
				}else{
					for(int k=0; k<snapshot.getHostList().get(i).getVulnList().get(j).getCveList().size(); k++){
						plot.setGreyEdition(true);
						drawRectNormalEdition(i, j, plot, snapshot);
					}
				}
			}else{
				if(expCheck.isSelected()){
					if(snapshot.getHostList().get(i).getVulnList().get(j).isExposed()){
						plot.setGreyEdition(false);
						drawRectNormalEdition(i, j, plot, snapshot);
					}else{
						if(snapshot.isExposed() == false){
							plot.setGreyEdition(true);
							drawRectNormalEdition(i, j, plot, snapshot);
						}
					}
				}else{
					plot.setGreyEdition(true);
					drawRectNormalEdition(i, j, plot, snapshot);
				}
			}
		}else{
			if(cveCheck.isSelected()){
				for(int k=0; k<snapshot.getHostList().get(i).getVulnList().get(j).getCveList().size(); k++){
					plot.setGreyEdition(true);
					drawRectBenchmarkEdition(idRif, i, j, plot, snapshot);
				}
			}else{
				plot.setGreyEdition(true);
				drawRectBenchmarkEdition(idRif, i, j, plot, snapshot);
			}
		}
	}

	/**
	 * creates labels with the number of vulns, date and id for each snapshot
	 */
	private void updateLabelValuesAfterDrawnABar(DrawPlot plot, Snapshot snapshot) {
		// TODO Auto-generated method stub
		JLabel vulnNumLabel = new JLabel(Integer.toString(plot.getAltezza()));
		vulnNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vulnNumLabel.setBounds((snapshot.getID()*100) + 55, (int)Math.ceil(230 - plot.getAltezza()/plot.getAdjust()), 40, 15);
		getFrmAnalisiReportQualys().getContentPane().add(vulnNumLabel);
		numVulnLabelList.add(vulnNumLabel);

		for(int i=0; i<snapshotList.size(); i++){
			if(snapshot.getID() == snapshotList.get(i).getID()){
				JLabel dataSnap = new JLabel("<html><center>"+Integer.toString(i+1)+"</center><br>"+snapshot.getDataSnapshot().get(0).getGiorno()+"/"+snapshot.getDataSnapshot().get(0).getMese()+"/"+snapshot.getDataSnapshot().get(0).getAnno()+"</html>");
				dataSnap.setHorizontalAlignment(SwingConstants.CENTER);
				dataSnap.setBounds((snapshot.getID()*100) + 40, 260, 75, 45);
				getFrmAnalisiReportQualys().getContentPane().add(dataSnap);
				getDataSnapList().add(dataSnap);
			}
		}
	}

	/**
	 * fills the object StatoAttuale for report functions
	 */
	private void statusUpdate(DrawPlot plot) {
		// TODO Auto-generated method stub
		status.getTotalNumberOfElements().add(plot.getAltezza());
		status.getTotalNumberOfVulns().add(contaVuln);
		status.getTotalNumberOfPract().add(contaPract);
		status.getTotalNumberOfServ().add(contaServ);
		status.getTotalNumberOfInf().add(contaInf);
		status.getNumberOfInfoVulns().add(plot.getAltezzaInfo());
		status.getNumberOfLowVulns().add(plot.getAltezzaLow());
		status.getNumberOfMediumVulns().add(plot.getAltezzaMedium());
		status.getNumberOfHighVulns().add(plot.getAltezzaHigh());
		status.getNumberOfCriticalVulns().add(plot.getAltezzaCritical());
	}

	/**
	 * conditions for the normal case
	 */
	private void drawRectNormalEdition(int i, int j, DrawPlot plot, Snapshot snapshot) {
		// TODO Auto-generated method stub
		if(vulnCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 1){
			contaVuln++;
			drawRect(snapshot, i, j, plot);
		}else if(practCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 2){
			contaPract++;
			drawRect(snapshot, i, j, plot);
		}else if(servCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 3){
			contaServ++;
			drawRect(snapshot, i, j, plot);
		}else if(infoCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 4){
			contaInf++;
			drawRect(snapshot, i, j, plot);
		}
	}

	/**
	 *  conditions for the benchmark case
	 */
	private void drawRectBenchmarkEdition(int idRif, int i, int j, DrawPlot plot, Snapshot snapshot) {
		// TODO Auto-generated method stub
		for(int k=0; k<snapshotList.get(idRif).getHostList().size(); k++){
			for(int m=0; m<snapshotList.get(idRif).getHostList().get(k).getVulnList().size(); m++){
				if(snapshotList.get(idRif).getHostList().get(k).getIPAddress().equals(snapshot.getHostList().get(i).getIPAddress())
						&&snapshotList.get(idRif).getHostList().get(k).getVulnList().get(m).getNumber().equals(snapshot.getHostList().get(i).getVulnList().get(j).getNumber())
						&&snapshotList.get(idRif).getHostList().get(k).getVulnList().get(m).getServiceName().equals(snapshot.getHostList().get(i).getVulnList().get(j).getServiceName())
						&&snapshotList.get(idRif).getHostList().get(k).getVulnList().get(m).getPortNumber().equals(snapshot.getHostList().get(i).getVulnList().get(j).getPortNumber())
						&&snapshotList.get(idRif).getHostList().get(k).getVulnList().get(m).getProtocol().equals(snapshot.getHostList().get(i).getVulnList().get(j).getProtocol())){
					if(vulnCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 1){					
						drawRect(snapshot, i, j, plot);
						contaVuln++;
						break;
					}
					if(practCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 2){		
						drawRect(snapshot, i, j, plot);
						contaPract++;
						break;
					}
					if(servCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 3){				
						drawRect(snapshot, i, j, plot);
						contaServ++;
						break;
					}
					if(infoCheck.isSelected()&&snapshot.getHostList().get(i).getVulnList().get(j).getType() == 4){	
						drawRect(snapshot, i, j, plot);
						contaInf++;
						break;
					}
				}
			}
		}
	}

	/**
	 * Alternative function triggered in case of "search" function  
	 * @param numVulnToSearch: vulnerability's number according to QID
	 * @param adjust: represents the normalized ratio for the bars' heights
	 */
	public void drawVulnerabilityPlotSearchEdition(Snapshot snapshot, String numVulnToSearch, double adjust) {
		// TODO Auto-generated method stub
		DrawPlot plot = new DrawPlot();
		contaVuln = 0;
		contaPract = 0;
		contaServ = 0;
		contaInf = 0;

		CDECheck.setSelected(false);
		PCICheck.setSelected(false);
		infoCheck.setSelected(false);
		servCheck.setSelected(false);
		practCheck.setSelected(false);
		vulnCheck.setSelected(false);
		plot.setAdjust(adjust);
		for(int i = 0; i < snapshot.getHostList().size(); i++){
			for(int j = 0; j < snapshot.getHostList().get(i).getVulnList().size(); j++){
				if(snapshot.getHostList().get(i).getVulnList().get(j).getNumber().equals(numVulnToSearch)){
					if(snapshot.getHostList().get(i).getVulnList().get(j).getType()==1){
						vulnCheck.setSelected(true);
						contaVuln++;
					}else if(snapshot.getHostList().get(i).getVulnList().get(j).getType()==2){
						practCheck.setSelected(true);
						contaPract++;
					}else if(snapshot.getHostList().get(i).getVulnList().get(j).getType()==3){
						servCheck.setSelected(true);
						contaServ++;
					}else if(snapshot.getHostList().get(i).getVulnList().get(j).getType()==4){
						infoCheck.setSelected(true);
						contaInf++;
					}
					plot.setGreyEdition(true);
					drawRect(snapshot, i, j, plot);
				}
			}
		}

		plot.sommaAltezze();
		getFrmAnalisiReportQualys().getContentPane().add(plot);
		plot.setSize(100, 250);
		plot.setLocation((snapshot.getID()*100), 0);
		plot.setVisible(true);
		plotList.add(plot);

		if((contaVuln+contaPract+contaServ+contaInf)>getMaxNumVuln())
			setMaxNumVuln(contaVuln + contaPract + contaServ + contaInf);

		updateLabelValuesAfterDrawnABar(plot, snapshot);
		statusUpdate(plot);
	}

	/**
	 * changes the background color in function of the vulnerability's signature used.
	 * it alternates 2 different shades of grey
	 */
	private void drawSignature() {
		// TODO Auto-generated method stub
		String versione="";
		int terzoValore=245;

		for(int i=0; i<snapshotList.size(); i++){
			int ampiezzaParziale=0;

			for(int j=0; j<snapshotList.get(i).getDataSnapshot().size(); j++){
				for(int k=0; k<2; k++){
					DrawSignature signa = new DrawSignature();

					if(!versione.equals(snapshotList.get(i).getOption().getSignatureList().get(j))){
						if(terzoValore==245)
							terzoValore = terzoValore - 20;
						else
							terzoValore = terzoValore + 20;
						signa.setColor(new Color(terzoValore,terzoValore,terzoValore));
					}else
						signa.setColor(new Color(terzoValore,terzoValore,terzoValore));

					if((j == snapshotList.get(i).getDataSnapshot().size()-1)&&(j == 0)){
						signa.setBorderS(true);
						signa.setAmpiezza(102 - ampiezzaParziale);
						signa.setLocation((snapshotList.get(i).getID()*100 + 25*(j + 1)), 50 + k*300);

					}else if(j == 0){
						signa.setBorderS(true);
						signa.setAmpiezza(40);
						signa.setLocation((snapshotList.get(i).getID()*100 + 25*(j + 1)), 50 + k*300);
						ampiezzaParziale = 40;
					}else if(j == snapshotList.get(i).getDataSnapshot().size()-1){
						signa.setBorderS(true);
						signa.setAmpiezza(118 - ampiezzaParziale);
						signa.setLocation((snapshotList.get(i).getID()*100 + 25*(j + 1)-3), 50 + k*300);
					}else{
						signa.setBorderS(false);
						signa.setAmpiezza(10);
						signa.setLocation((snapshotList.get(i).getID()*100 + 42 + 10*(j + 1)), 50 + k*300);
						ampiezzaParziale = ampiezzaParziale + 10;
					}
					versione = snapshotList.get(i).getOption().getSignatureList().get(j);

					signa.setSize(signa.getAmpiezza(), 220);
					signa.setToolTipText(snapshotList.get(i).getOption().getSignatureList().get(j));
					getFrmAnalisiReportQualys().getContentPane().add(signa);
					signa.setVisible(true);		
					sinatureList.add(signa);
				}
			}
		}
	}


	/**
	 * increases the height of the bar 
	 */
	private void drawRect(Snapshot snapshot, int i, int j, DrawPlot plot) {
		// TODO Auto-generated method stub
		if(snapshot.getHostList().get(i).getVulnList().get(j).getCvss().isEmpty())
			plot.setAltezzaInfo(plot.getAltezzaInfo() + 1);
		else if((Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10>=1)&&(Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10<=39))
			plot.setAltezzaLow(plot.getAltezzaLow() + 1);
		else if((Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10>=40)&&(Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10<=69))
			plot.setAltezzaMedium(plot.getAltezzaMedium() + 1);
		else if((Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10>=70)&&(Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10<=90))
			plot.setAltezzaHigh(plot.getAltezzaHigh() + 1);
		else if((Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10>=9.1)&&(Float.parseFloat(snapshot.getHostList().get(i).getVulnList().get(j).getCvss())*10<=100))
			plot.setAltezzaCritical(plot.getAltezzaCritical() + 1);
	}

	/**
	 * hides the old components before starting a new visualization
	 */
	private void puliziaGrafici() {
		// TODO Auto-generated method stub
		for(int k=0; k<plotList.size(); k++){
			plotList.get(k).setVisible(false);
		}
		for(int k=0; k<numVulnLabelList.size(); k++){
			numVulnLabelList.get(k).setVisible(false);
		}
		for(int k=0; k<dataSnapList.size(); k++){
			dataSnapList.get(k).setVisible(false);
		}
		for(int k=0; k<numHostTested.size(); k++){
			numHostTested.get(k).setVisible(false);
		}
		for(int k=0; k<sinatureList.size(); k++){
			sinatureList.get(k).setVisible(false);
		}
		for(int i=0; i<snapshotList.size();i++){
			for(int j=0; j<snapshotList.get(i).getHostList().size(); j++){
				if(snapshotList.get(i).getHostList().get(j).getPosizioneHostRange()!=null)
					snapshotList.get(i).getHostList().get(j).getPosizioneHostRange().setVisible(false);
			}
		}
		setAltezzaLabelIPmin(0);
		setAltezzaLabelIPmax(0);
	}

	/**
	 * combines strings for the report file's name
	 */
	public void setNomeStatoAttuale() {
		// TODO Auto-generated method stub
		if(CDECheck.isSelected())
			status.setAzione(status.getAzione()+"_CDE");
		if(PCICheck.isSelected())
			status.setAzione(status.getAzione()+"_PCI");
		if(numVulnToSearch != "")
			status.setAzione(status.getAzione()+"_vuln_"+numVulnToSearch);
		if(idRifInteger != 0)
			status.setAzione(status.getAzione()+"_benchmark_"+idRifInteger);
		if(vulnCheck.isSelected())
			status.setAzione(status.getAzione()+"_VULNS");
		if(practCheck.isSelected())
			status.setAzione(status.getAzione()+"_PRACTICES");
		if(servCheck.isSelected())
			status.setAzione(status.getAzione()+"_SERVICES");
		if(infoCheck.isSelected())
			status.setAzione(status.getAzione()+"_INFOS");
		if(cveCheck.isSelected())
			status.setAzione(status.getAzione()+"_CVE");
		if(expCheck.isSelected())
			status.setAzione(status.getAzione()+"_exposed");
	}

	public ReadReport getRead() {
		return read;
	}

	public void setRead(ReadReport read) {
		this.read = read;
	}

	public JFrame getFrmAnalisiReportQualys() {
		return frmAnalisiReportQualys;
	}

	public void setFrmAnalisiReportQualys(JFrame frmAnalisiReportQualys) {
		this.frmAnalisiReportQualys = frmAnalisiReportQualys;
	}

	public int getAltezzaLabelIPmin() {
		return altezzaLabelIPmin;
	}

	public void setAltezzaLabelIPmin(int altezzaLabelIPmin) {
		this.altezzaLabelIPmin = altezzaLabelIPmin;
	}

	public int getAltezzaLabelIPmax() {
		return altezzaLabelIPmax;
	}

	public void setAltezzaLabelIPmax(int altezzaLabelIPmax) {
		this.altezzaLabelIPmax = altezzaLabelIPmax;
	}

	public int getNumberOfSnap() {
		return numberOfSnap;
	}

	public void setNumberOfSnap(int numberOfSnap) {
		this.numberOfSnap = numberOfSnap;
	}

	public List<String> getCDEHost() {
		return CDEHost;
	}

	public void setCDEHost(List<String> cDEHost) {
		CDEHost = cDEHost;
	}

	public JLabel getLabel_3() {
		return label_3;
	}

	public void setLabel_3(JLabel label_3) {
		this.label_3 = label_3;
	}

	public JLabel getLabel_46() {
		return label_46;
	}

	public void setLabel_46(JLabel label_46) {
		this.label_46 = label_46;
	}

	public JLabel getExitList() {
		return exitList;
	}

	public void setExitList(JLabel exitList) {
		this.exitList = exitList;
	}

	public int getMaxNumVuln() {
		return maxNumVuln;
	}

	public void setMaxNumVuln(int maxNumVuln) {
		this.maxNumVuln = maxNumVuln;
	}

	public List<JLabel> getNumHostTested() {
		return numHostTested;
	}

	public void setNumHostTested(List<JLabel> numHostTested) {
		this.numHostTested = numHostTested;
	}

	public Finestra getFinestra() {
		return finestra;
	}

	public void setFinestra(Finestra finestra) {
		this.finestra = finestra;
	}

	public JCheckBox getCDECheck() {
		return CDECheck;
	}

	public void setCDECheck(JCheckBox cDECheck) {
		CDECheck = cDECheck;
	}

	public String getNumVulnToSearch() {
		return numVulnToSearch;
	}

	public void setNumVulnToSearch(String numVulnToSearch) {
		this.numVulnToSearch = numVulnToSearch;
	}


	public List<JLabel> getDataSnapList() {
		return dataSnapList;
	}


	public void setDataSnapList(List<JLabel> dataSnapList) {
		this.dataSnapList = dataSnapList;
	}

	public JCheckBox getResetCheck() {
		return resetCheck;
	}

	public void setResetCheck(JCheckBox resetCheck) {
		this.resetCheck = resetCheck;
	}

	public List<DrawSignature> getSinatureList() {
		return sinatureList;
	}

	public void setSinatureList(List<DrawSignature> sinatureList) {
		this.sinatureList = sinatureList;
	}

	public JCheckBox getVulnCheck() {
		return vulnCheck;
	}

	public void setVulnCheck(JCheckBox vulnCheck) {
		this.vulnCheck = vulnCheck;
	}

	public JCheckBox getPractCheck() {
		return practCheck;
	}

	public void setPractCheck(JCheckBox practCheck) {
		this.practCheck = practCheck;
	}

	public JCheckBox getServCheck() {
		return servCheck;
	}

	public void setServCheck(JCheckBox servCheck) {
		this.servCheck = servCheck;
	}

	public JCheckBox getInfCheck() {
		return infoCheck;
	}

	public void setInfCheck(JCheckBox infCheck) {
		this.infoCheck = infCheck;
	}

	public JCheckBox getCveCheck() {
		return cveCheck;
	}

	public void setCveCheck(JCheckBox cveCheck) {
		this.cveCheck = cveCheck;
	}

	public List<ExposedElement> getExposedList() {
		return ExposedList;
	}

	public void setExposedList(List<ExposedElement> exposedList) {
		ExposedList = exposedList;
	}

}
