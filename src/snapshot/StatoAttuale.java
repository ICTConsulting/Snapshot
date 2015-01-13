package snapshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * each parameter of this object represent the current visualization state. This is done for report writing functions  
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class StatoAttuale {

	private List<Integer> totalNumberOfVulns;
	private List<Integer> totalNumberOfPract;
	private List<Integer> totalNumberOfServ;
	private List<Integer> totalNumberOfInf;
	private List<Integer> totalNumberOfElements;
	private List<Integer> numberOfInfoVulns;
	private List<Integer> numberOfLowVulns;
	private List<Integer> numberOfMediumVulns;
	private List<Integer> numberOfHighVulns;
	private List<Integer> numberOfCriticalVulns;
	private List<Integer> numberOfTestedHost;
	private List<Snapshot> snapshotList;
	private String azione = "";

	public StatoAttuale(){
		setTotalNumberOfVulns(new ArrayList<Integer>());
		setTotalNumberOfPract(new ArrayList<Integer>());
		setTotalNumberOfServ(new ArrayList<Integer>());
		setTotalNumberOfInf(new ArrayList<Integer>());
		setTotalNumberOfElements(new ArrayList<Integer>());
		setNumberOfInfoVulns(new ArrayList<Integer>());
		setNumberOfLowVulns(new ArrayList<Integer>());
		setNumberOfMediumVulns(new ArrayList<Integer>());
		setNumberOfHighVulns(new ArrayList<Integer>());
		setNumberOfCriticalVulns(new ArrayList<Integer>());
		setNumberOfTestedHost(new ArrayList<Integer>());
		setSnapshotList(new ArrayList <Snapshot>());
	}

	/**
	 * the formatting structure of the report
	 */
	public boolean scriviReport(){
		boolean success = false;
		String current = System.getProperty("user.dir");
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String nomeFile = timestamp.toString().replace(":", "-");
		nomeFile = nomeFile.replace(" ", "-");
		nomeFile=nomeFile.substring(0, 19);
		File f = new File(current+"/report/["+nomeFile+"]"+this.getAzione()+".txt");
		FileWriter fw;
		try {
			fw = new FileWriter(f);
			fw.write("Snapshot:");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("\t\t" + Integer.toString(i+1));
			fw.write("\r\n");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("---------------------");

			fw.write("\r\n");
			fw.write("\r\nDate:\t\t");
			for(int i=0; i<this.getSnapshotList().size(); i++){
				fw.write("\t"+this.getSnapshotList().get(i).getDataSnapshot().get(0).getGiorno()+"/"+this.getSnapshotList().get(i).getDataSnapshot().get(0).getMese()+"/"+this.getSnapshotList().get(i).getDataSnapshot().get(0).getAnno());
			}
			fw.write("\r\n");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("---------------------");

			fw.write("\r\n");
			fw.write("\r\nThreat level");

			fw.write("\r\nInfo:\t");
			for(int i=0; i<this.getNumberOfInfoVulns().size(); i++)
				fw.write("\t\t"+this.getNumberOfInfoVulns().get(i));

			fw.write("\r\nLow:\t");
			for(int i=0; i<this.getNumberOfLowVulns().size(); i++)
				fw.write("\t\t"+this.getNumberOfLowVulns().get(i));

			fw.write("\r\nMedium:\t");
			for(int i=0; i<this.getNumberOfMediumVulns().size(); i++)
				fw.write("\t\t"+this.getNumberOfMediumVulns().get(i));

			fw.write("\r\nHigh:\t");
			for(int i=0; i<this.getNumberOfHighVulns().size(); i++)
				fw.write("\t\t"+this.getNumberOfHighVulns().get(i));

			fw.write("\r\nCritic:\t");
			for(int i=0; i<this.getNumberOfCriticalVulns().size(); i++)
				fw.write("\t\t"+this.getNumberOfCriticalVulns().get(i));

			fw.write("\r\nTotal amount:\t\t");
			for(int i=0; i<this.getTotalNumberOfElements().size(); i++)
				fw.write(this.getTotalNumberOfElements().get(i)+"\t\t" );

			fw.write("\r\n");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("---------------------");
			fw.write("\r\n\r\nWhich");
			fw.write("\r\nVULNS:\t");
			for(int i=0; i<this.getTotalNumberOfVulns().size(); i++)
				fw.write("\t\t" + this.getTotalNumberOfVulns().get(i));
			
			fw.write("\r\nPRACTICES:");
			for(int i=0; i<this.getTotalNumberOfPract().size(); i++)
				fw.write("\t\t" + this.getTotalNumberOfPract().get(i));
			
			fw.write("\r\nSERVICES:");
			for(int i=0; i<this.getTotalNumberOfServ().size(); i++)
				fw.write("\t\t" + this.getTotalNumberOfServ().get(i));
			
			fw.write("\r\nINFOS:\t");
			for(int i=0; i<this.getTotalNumberOfInf().size(); i++)
				fw.write("\t\t" + this.getTotalNumberOfInf().get(i));
			
			fw.write("\r\n");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("---------------------");

			fw.write("\r\n");
			fw.write("\r\nNumber of host:");
			for(int i=0; i<this.getNumberOfTestedHost().size(); i++){
				fw.write("\t\t"+this.getNumberOfTestedHost().get(i));
			}
			fw.write("\r\n");
			for(int i=0; i<this.getSnapshotList().size(); i++)
				fw.write("---------------------");
			
			List<Host> reportHostList = new ArrayList<Host>();
			for(int i=0; i<this.getSnapshotList().size(); i++){
				for(int j=0; j<this.getSnapshotList().get(i).getHostList().size(); j++)
					if(this.getSnapshotList().get(i).getHostList().get(j).getPosizioneHostRange().isVisible()==true){
						this.getSnapshotList().get(i).getHostList().get(j).setSnapShotDiRiferimento(i);
						reportHostList.add(this.getSnapshotList().get(i).getHostList().get(j));
					}
			}
			Collections.sort(reportHostList, new Comparator<Host>() {
				public int compare(Host host1, Host host2)
				{
					return  Long.toString(host1.getNum()).compareTo(Long.toString(host2.getNum()));
				}
			});
			
			fw.write("\r\n\t\t\t");
			for(int k=0; k<reportHostList.get(0).getSnapShotDiRiferimento(); k++)
				fw.write("\t\t");
			fw.write(reportHostList.get(0).getIPAddress());
			for(int i=1; i<reportHostList.size();i++){
				if(reportHostList.get(i).getNum()!=reportHostList.get(i-1).getNum()){
					fw.write("\r\n\t\t\t");
					for(int k=0; k<reportHostList.get(i).getSnapShotDiRiferimento(); k++)
						fw.write("\t\t");
					fw.write(reportHostList.get(i).getIPAddress());
				}else{
					fw.write("\t"+reportHostList.get(i).getIPAddress());
				}
			}

			fw.flush();
			fw.close();
			success = true;
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	public List<Integer> getTotalNumberOfVulns() {
		return totalNumberOfVulns;
	}

	public void setTotalNumberOfVulns(List<Integer> totalNumberOfVulns) {
		this.totalNumberOfVulns = totalNumberOfVulns;
	}

	public List<Integer> getNumberOfInfoVulns() {
		return numberOfInfoVulns;
	}

	public void setNumberOfInfoVulns(List<Integer> numberOfInfoVulns) {
		this.numberOfInfoVulns = numberOfInfoVulns;
	}

	public List<Integer> getNumberOfLowVulns() {
		return numberOfLowVulns;
	}

	public void setNumberOfLowVulns(List<Integer> numberOfLowVulns) {
		this.numberOfLowVulns = numberOfLowVulns;
	}

	public List<Integer> getNumberOfMediumVulns() {
		return numberOfMediumVulns;
	}

	public void setNumberOfMediumVulns(List<Integer> numberOfMediumVulns) {
		this.numberOfMediumVulns = numberOfMediumVulns;
	}

	public List<Integer> getNumberOfHighVulns() {
		return numberOfHighVulns;
	}

	public void setNumberOfHighVulns(List<Integer> numberOfHighVulns) {
		this.numberOfHighVulns = numberOfHighVulns;
	}

	public List<Integer> getNumberOfCriticalVulns() {
		return numberOfCriticalVulns;
	}

	public void setNumberOfCriticalVulns(List<Integer> numberOfCriticalVulns) {
		this.numberOfCriticalVulns = numberOfCriticalVulns;
	}

	public List<Snapshot> getSnapshotList() {
		return snapshotList;
	}

	public void setSnapshotList(List<Snapshot> snapshotList) {
		this.snapshotList = snapshotList;
	}

	public List<Integer> getNumberOfTestedHost() {
		return numberOfTestedHost;
	}

	public void setNumberOfTestedHost(List<Integer> numberOfTestedHost) {
		this.numberOfTestedHost = numberOfTestedHost;
	}

	public String getAzione() {
		return azione;
	}

	public void setAzione(String azione) {
		this.azione = azione;
	}

	public List<Integer> getTotalNumberOfPract() {
		return totalNumberOfPract;
	}

	public void setTotalNumberOfPract(List<Integer> totalNumberOfPract) {
		this.totalNumberOfPract = totalNumberOfPract;
	}

	public List<Integer> getTotalNumberOfServ() {
		return totalNumberOfServ;
	}

	public void setTotalNumberOfServ(List<Integer> totalNumberOfServ) {
		this.totalNumberOfServ = totalNumberOfServ;
	}

	public List<Integer> getTotalNumberOfInf() {
		return totalNumberOfInf;
	}

	public void setTotalNumberOfInf(List<Integer> totalNumberOfInf) {
		this.totalNumberOfInf = totalNumberOfInf;
	}

	public List<Integer> getTotalNumberOfElements() {
		return totalNumberOfElements;
	}

	public void setTotalNumberOfElements(List<Integer> totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}

}
