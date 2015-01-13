package snapshot;

/**
 * information about the single CVE-ID read in the Qualys report for each element
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class Cve {

	private String ID;
	private boolean exposed;
	
	public Cve(String iD, boolean exposed) {
		super();
		ID = iD;
		this.exposed = exposed;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public boolean isExposed() {
		return exposed;
	}
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}	
}
