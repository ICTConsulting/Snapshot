package snapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * this class stores info about the configuration during a single analysis
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class Option {
	private List<String> optionList = new ArrayList<String>();
	private List<String> signatureList = new ArrayList<String>();
	private List<String> scannerList = new ArrayList<String>();
	private boolean differenceOptionFlag = false;
	private List<String> title = new ArrayList<String>();
	private List<String> durata = new ArrayList<String>();
	private List<String> status = new ArrayList<String>();
	private List<Integer> totalHost = new ArrayList<Integer>();
	private List<Integer> aliveHost = new ArrayList<Integer>();
	

	public List<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}

	public List<String> getSignatureList() {
		return signatureList;
	}

	public void setSignatureList(List<String> signature) {
		this.signatureList = signature;
	}

	public boolean isDifferenceOptionFlag() {
		return differenceOptionFlag;
	}

	public void setDifferenceOptionFlag(boolean differenceOptionFlag) {
		this.differenceOptionFlag = differenceOptionFlag;
	}

	public List<String> getTitle() {
		return title;
	}

	public void setTitle(List<String> title) {
		this.title = title;
	}

	public List<String> getDurata() {
		return durata;
	}

	public void setDurata(List<String> durata) {
		this.durata = durata;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public List<Integer> getTotalHost() {
		return totalHost;
	}

	public void setTotalHost(List<Integer> totalHost) {
		this.totalHost = totalHost;
	}

	public List<Integer> getAliveHost() {
		return aliveHost;
	}

	public void setAliveHost(List<Integer> aliveHost) {
		this.aliveHost = aliveHost;
	}

	public List<String> getScannerList() {
		return scannerList;
	}

	public void setScannerList(List<String> scannerList) {
		this.scannerList = scannerList;
	}	

}
