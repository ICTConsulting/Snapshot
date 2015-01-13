package snapshot;

/**
 * the structure stores info about the exposed elements read in the config.xml file
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class ExposedElement {
	
	private String snapshotID;
	private String IPAddress;
	private String portNumber;
	private String number;
	private String cve;

	public ExposedElement(String snapshotID, String iPAddress, String portNumber, String number, String cve) {
		super();
		this.setSnapshotID(snapshotID);
		setIPAddress(iPAddress);
		this.setPortNumber(portNumber);
		this.setNumber(number);
		this.setCve(cve);
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSnapshotID() {
		return snapshotID;
	}

	public void setSnapshotID(String snapshotID) {
		this.snapshotID = snapshotID;
	}

	public String getCve() {
		return cve;
	}

	public void setCve(String cve) {
		this.cve = cve;
	}

}
