package snapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * the root object representing a single snapshot 
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class Snapshot {
	
	private List<Host> hostList;
	private List<Host> excludedHostList;
	private Option option;
	private List<Data> dataSnapshot;
	private int ID;
	private boolean exposed;
	
	public Snapshot(int identificativo){
		this.ID = identificativo;
		this.setHostList(new ArrayList<Host>());
		this.setExcludedHostList(new ArrayList<Host>());
		this.setOption(new Option());
		this.setDataSnapshot(new ArrayList<Data>());
		this.setExposed(false);
	}

	public List<Host> getHostList() {
		return hostList;
	}

	public void setHostList(List<Host> hostList) {
		this.hostList = hostList;
	}

	public List<Host> getExcludedHostList() {
		return excludedHostList;
	}

	public void setExcludedHostList(List<Host> excludedHostList) {
		this.excludedHostList = excludedHostList;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public List<Data> getDataSnapshot() {
		return dataSnapshot;
	}

	public void setDataSnapshot(List<Data> dataSnapshot) {
		this.dataSnapshot = dataSnapshot;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

}
