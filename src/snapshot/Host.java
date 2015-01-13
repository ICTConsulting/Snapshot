package snapshot;

import java.util.ArrayList;
import java.util.List;

import drawing.DrawHostRange;

/**
 * represents a single host analyzed in a snapshot. It stores some information about the host such as OS, hostname, etc.. plus a list of vulnerabilities found on it
 * Copyright (c) 2014 ICT Consulting
 */
public class Host {
	private String IPAddress;
	private String name;
	private String os;
	private List<Vulnerability> vulnList;
	private int snapShotDiRiferimento = 0;
	private int primoByte;
	private int secondoByte;
	private int terzoByte;
	private int quartoByte;
	private Data dataAnalisi;
	private long num; 
	private DrawHostRange posizioneHostRange = null;
	

	public Host(){
		this.setVulnList(new ArrayList<Vulnerability>());
		this.IPAddress="";
		this.setOs("");
		this.setName("");
	}
	
	
	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}


	public List<Vulnerability> getVulnList() {
		return vulnList;
	}


	public void setVulnList(List<Vulnerability> vulnList) {
		this.vulnList = vulnList;
	}


	public int getPrimoByte() {
		return primoByte;
	}


	public void setPrimoByte(int primoByte) {
		this.primoByte = primoByte;
	}


	public int getSecondoByte() {
		return secondoByte;
	}


	public void setSecondoByte(int secondoByte) {
		this.secondoByte = secondoByte;
	}


	public int getTerzoByte() {
		return terzoByte;
	}


	public void setTerzoByte(int terzoByte) {
		this.terzoByte = terzoByte;
	}


	public int getQuartoByte() {
		return quartoByte;
	}


	public void setQuartoByte(int quartoByte) {
		this.quartoByte = quartoByte;
	}


	public Data getDataAnalisi() {
		return dataAnalisi;
	}


	public void setDataAnalisi(Data dataAnalisi) {
		this.dataAnalisi = dataAnalisi;
	}


	public long getNum() {
		return num;
	}


	public void setNum(long num) {
		this.num = num;
	}


	public DrawHostRange getPosizioneHostRange() {
		return posizioneHostRange;
	}


	public void setPosizioneHostRange(DrawHostRange posizioneHostRange) {
		this.posizioneHostRange = posizioneHostRange;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
	}


	public int getSnapShotDiRiferimento() {
		return snapShotDiRiferimento;
	}


	public void setSnapShotDiRiferimento(int snapShotDiRiferimento) {
		this.snapShotDiRiferimento = snapShotDiRiferimento;
	}
	
}
