package snapshot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * extract data from body.txt and header.txt in order to fill objects
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class ReadReport {

	private Snapshot snap;

	/**
	 * @param IDSnap: represent the snapshot's number to generate 
	 */
	public ReadReport(int IDSnap) {
		snap = new Snapshot(IDSnap);
		readHeaderFromFile(IDSnap, snap.getHostList(), snap.getExcludedHostList(), snap.getOption(), snap.getDataSnapshot());		  
		removeExcludedHost(snap.getHostList(), snap.getExcludedHostList());
		readVulnFromFile(IDSnap, snap.getHostList());
		Collections.sort(snap.getHostList(), new Comparator<Host>() {
			@Override
			public int compare(Host host1, Host host2)
			{
				return  Long.compare(host1.getNum(), host2.getNum());
			}
		});
	}

	/**
	 * reads body.txt which contains data about vulns, practices, infos and services
	 */
	private void readVulnFromFile(int ID, List<Host> hostList) {
		// TODO Auto-generated method stub
		String csvFileToRead = ID + "/body.txt";  
		BufferedReader br = null;
		String line = "";  
		String splitBy = "\t";  
		String info;
		int contaVuln = 0;
		int contaPract = 0;
		int contaServ = 0;
		int contaInf = 0;
		int type = 0;
		int contaCVEtot = 0;

		try {  
			br = new BufferedReader(new FileReader(csvFileToRead)); 

			while ((line = br.readLine()) != null) {  

				String[] record = line.split(splitBy);

				if(record.length>2){
					for(int i=0; i<hostList.size();i++){
						if(record[1].equals(hostList.get(i).getIPAddress())){
							if(record[2].equals("VULN")){
								contaVuln++;
								type = 1;
								Vulnerability vuln;
								hostList.get(i).setName(record[0]);

								if(record.length==12){
									info = record[10];
									hostList.get(i).setOs(record[11]);
								}else if(record.length == 11)
									info = record[10];
								else
									info = "0";

								if(record[5].equals("1")){
									vuln = new Vulnerability(record[3], record[4], record[6], record[7], record[8], true, record[9], info, type);
								}else{
									vuln = new Vulnerability(record[3], record[4], record[6], record[7], record[8], false, record[9], info, type);
								}

								if(record[7].length()>0)
									contaCVEtot = cveListSplitter(record[7], vuln, contaCVEtot);
								else
									vuln.getCveList().add(new Cve("", false));

								hostList.get(i).getVulnList().add(vuln);
							}else if(record[2].equals("PRACTICE")){
								contaPract++;
								type = 2;
								Vulnerability vuln;

								hostList.get(i).setName(record[0]);

								if(record.length==12){
									info = record[10];
									hostList.get(i).setOs(record[11]);
								}else if(record.length == 11)
									info = record[10];
								else
									info = "0";

								if(record[5].equals("1"))
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], true, record[9], info, type);
								else
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], false, record[9], info, type);

								if(record[7].length()>0)
									contaCVEtot = cveListSplitter(record[7], vuln, contaCVEtot);
								else
									vuln.getCveList().add(new Cve("", false));

								hostList.get(i).getVulnList().add(vuln);
							}else if(record[2].equals("SERVICE")){
								contaServ++;
								type = 3;
								Vulnerability vuln;

								hostList.get(i).setName(record[0]);

								if(record.length==12){
									info = record[10];
									hostList.get(i).setOs(record[11]);
								}else if(record.length == 11)
									info = record[10];
								else
									info = "0";

								if(record[5].equals("1"))
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], true, record[9], info, type);
								else
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], false, record[9], info, type);

								if(record[7].length()>0)
									contaCVEtot = cveListSplitter(record[7], vuln, contaCVEtot);
								else
									vuln.getCveList().add(new Cve("", false));
								
								hostList.get(i).getVulnList().add(vuln);
							}else if(record[2].equals("INFO")){
								contaInf++;
								type = 4;
								Vulnerability vuln;

								hostList.get(i).setName(record[0]);

								if(record.length==12){
									info = record[10];
									hostList.get(i).setOs(record[11]);
								}else if(record.length == 11)
									info = record[10];
								else
									info = "0";

								if(record[5].equals("1"))
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], true, record[9], info, type);
								else
									vuln = new Vulnerability(record[3],record[4],record[6],record[7],record[8], false, record[9], info, type);

								if(record[7].length()>0)
									contaCVEtot = cveListSplitter(record[7], vuln, contaCVEtot);
								else
									vuln.getCveList().add(new Cve("", false));
								
								hostList.get(i).getVulnList().add(vuln);
							}

						}
					}
				}
			}

			System.out.println("************* number of vulns: " + contaVuln + ", number of practices: " + contaPract + ", number of services: " + contaServ + ", number of infos: " + contaInf);
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}

	private int cveListSplitter(String cveList, Vulnerability vuln, int contaCVEtot) {
		// TODO Auto-generated method stub
		if(cveList.length() > 14){		
			while(cveList.length()>=13){
				contaCVEtot++;
				vuln.getCveList().add(new Cve(cveList.substring(cveList.lastIndexOf("C"), cveList.length()), false));
				if(cveList.length()>13){
					cveList = cveList.substring(0, cveList.lastIndexOf(","));
				}else{
					cveList="";
				}
			}					
		}else if(cveList.length() == 14){
			contaCVEtot++;
			vuln.getCveList().add(new Cve(cveList.substring(0, 13), false));
		}else if(cveList.length() == 13){
			contaCVEtot++;
			vuln.getCveList().add(new Cve(cveList.substring(0, 13), false));
		}
		return contaCVEtot;
	}

	/**
	 * reads header.txt in order to extract data about Qualys's analysis
	 */
	private static void readHeaderFromFile(int ID, List<Host> hostList, List<Host> excludedHostList, Option option, List<Data> dataList) {
		// TODO Auto-generated method stub
		String csvFileToRead = ID+"/header.txt";  
		BufferedReader br = null;  
		String line = "";  
		String splitBy = "\t";  

		try {  

			br = new BufferedReader(new FileReader(csvFileToRead));  
			while ((line = br.readLine()) != null) {  
				String[] hostAddress = line.split(splitBy);  
				if(hostAddress[0].equals("Date")){
					Data data = new Data(hostAddress[1].substring(0, 4),
							hostAddress[1].substring(5, 7),
							hostAddress[1].substring(8, 10),
							hostAddress[1].substring(11, 13),
							hostAddress[1].substring(14, 16));
					dataList.add(data);
				}else if(hostAddress[0].equals("Host range"))
					hostExtractor(hostAddress, hostList, "Host range", dataList);
				else if(hostAddress[0].equals("Host excluded")){
					hostExtractor(hostAddress, excludedHostList, "Host excluded", dataList);
				}else if(hostAddress[0].equals("Configuration option")){
					if(optionExtractor(hostAddress, option, "Configuration option") == true)
						option.setDifferenceOptionFlag(true);
				}else if(hostAddress[0].equals("Vulnerability Signature Version")){
					option.getScannerList().add(hostAddress[1].substring(hostAddress[1].indexOf("(")+1, hostAddress[1].indexOf(",")));
					option.getSignatureList().add("Vuln. sign.:"+hostAddress[1].substring(hostAddress[1].indexOf(",")+26, hostAddress[1].indexOf(")")));
				}else if(hostAddress[0].equals("Title")){
					option.getTitle().add(hostAddress[1]);
				}else if(hostAddress[0].equals("Duration")){
					option.getDurata().add(hostAddress[1]);
				}else if(hostAddress[0].equals("Status")){
					option.getStatus().add(hostAddress[1]);
				}else if(hostAddress[0].equals("Number of responding host")){
					option.getAliveHost().add(Integer.parseInt(hostAddress[1]));
				}else if(hostAddress[0].equals("Total number of host")){
					option.getTotalHost().add(Integer.parseInt(hostAddress[1]));
				}
			}  

		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (br != null) {  
				try {  
					br.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}

	/**
	 * extracts the configuration and the signature version 
	 */
	private static boolean optionExtractor(String[] hostAddress, Option option, String parameter) {
		// TODO Auto-generated method stub
		int numberOfTarget = 1;
		boolean flag = false;
		List<String> optList = option.getOptionList();

		if(parameter.equals("Configuration option")){
			if(optList.isEmpty()){
				while(!hostAddress[numberOfTarget].equals(";")){
					optList.add(hostAddress[numberOfTarget]);
					numberOfTarget++;
				}
			}else{
				while(!hostAddress[numberOfTarget].equals(";")){
					if(!optList.contains(hostAddress[numberOfTarget]))
						flag = true;
					numberOfTarget++;
				}
			}
		}
		return flag;
	}

	/**
	 * receives as input a string containing IP addresses, then crate an Host object for each of them
	 */
	private static void hostExtractor(String[] hostAddress, List<Host> list, String fieldName, List<Data> dataList) {
		// TODO Auto-generated method stub
		if(hostAddress[0].equals(fieldName)){
			int numberOfTarget = 1;
			while(!hostAddress[numberOfTarget].equals(";")){
				if(hostAddress[numberOfTarget].contains("-")){
					splitTargetRange((String)hostAddress[numberOfTarget], list, dataList);
				}else{
					if(!hostAddress[numberOfTarget].equals("N/A")){
						Host newHost = new Host();
						newHost.setIPAddress(hostAddress[numberOfTarget]);
						byteExtractor(hostAddress[numberOfTarget], newHost);
						newHost.setDataAnalisi(dataList.get(dataList.size()-1));

						/**
						 * additional check to remove IP address already created
						 */
						boolean check = false;
						for(int k=0; k<list.size();k++){
							if(newHost.getIPAddress().equals(list.get(k).getIPAddress())){
								check=true;
							}
						}
						if(check==false)
							list.add(newHost);
					}
				}
				numberOfTarget++;
			}
		}
	}


	/**
	 * removes those addresses contained in the excluded tag
	 */
	private static void removeExcludedHost(List<Host> hostList,	List<Host> excludedHostList) {
		// TODO Auto-generated method stub
		for(int i=0; i < excludedHostList.size(); i++){
			for(int j=0; j < hostList.size(); j++){
				if(excludedHostList.get(i).getIPAddress().equals(hostList.get(j).getIPAddress())){
					hostList.remove(j);
				}
			}
		}		
	}


	/**
	 * split a range of IP addresses (x.x.x.x-x.x.x.y) in single IP address values
	 */
	private static void splitTargetRange(String hostAddress, List<Host> list, List<Data> dataList) {
		// TODO Auto-generated method stub
		String begin = hostAddress.substring(0, hostAddress.indexOf("-"));
		String end = hostAddress.substring(hostAddress.indexOf("-") + 1, hostAddress.length());
		int numberOfHostInRange = Integer.parseInt(end.substring(end.lastIndexOf(".") + 1, end.length())) - Integer.parseInt(begin.substring(begin.lastIndexOf(".") + 1, begin.length()));

		for(int i=0; i<=numberOfHostInRange; i++){
			Host host = new Host();
			host.setIPAddress(begin.substring(0,begin.lastIndexOf(".") + 1).concat(Integer.toString(Integer.parseInt(begin.substring(begin.lastIndexOf(".") + 1, begin.length()))+i)));
			byteExtractor(host.getIPAddress(),host);
			host.setDataAnalisi(dataList.get(dataList.size()-1));
			boolean check=false;
			for(int k=0; k<list.size();k++){
				if(host.getIPAddress().equals(list.get(k).getIPAddress()))
					check=true;
			}
			if(check==false)
				list.add(host);
		}
	}

	/**
	 * extract the 4 bytes on an IP address in order to compute a the number attribute of an Host object
	 * this parameter is needed to sort IP addresses
	 */
	private static void byteExtractor(String IPAddress, Host host) {
		int byteExtracted = 0;

		for(int i=0; i<3; i++){
			byteExtracted = Integer.parseInt(IPAddress.substring(0, IPAddress.indexOf(".")));
			IPAddress = IPAddress.substring(IPAddress.indexOf(".")+1);
			switch(i){
			case 0:
				host.setPrimoByte(byteExtracted);
				break;
			case 1:
				host.setSecondoByte(byteExtracted);
				break;
			case 2:
				host.setTerzoByte(byteExtracted);
				break;
			}
		}
		host.setQuartoByte(Integer.parseInt(IPAddress));
		host.setNum((long) (host.getPrimoByte()*Math.pow(256, 3) + host.getSecondoByte()*Math.pow(256, 2)+host.getTerzoByte()*(256)+host.getQuartoByte()));
	}

	public Snapshot getSnap() {
		return snap;
	}

	public void setSnap(Snapshot snapshot) {
		this.snap = snapshot;
	}

}
