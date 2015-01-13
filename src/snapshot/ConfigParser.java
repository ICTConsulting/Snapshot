package snapshot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * this class permits to extract the CDE host composition from the config.xml file
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class ConfigParser {
	static Document doc;
	private List<String> CDEHost = new ArrayList<String>();
	private List<ExposedElement> ExpEl = new ArrayList<ExposedElement>();
	private String currentID = ""; 

	public ConfigParser() {
		String filename = "config.xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();      

		try {
			DocumentBuilder builder = dbf.newDocumentBuilder();
			File xmlFile = new File(filename);
			doc = builder.parse(xmlFile);
			this.printNodeInfo(doc);
		} catch (SAXException sxe) {
			Exception  x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}


	/**
	 * recursively extract IP addresses
	 */
	public void printNodeInfo(Node currentNode) {
		short sNodeType = currentNode.getNodeType();

		if (sNodeType == Node.ELEMENT_NODE) {
			String sNodeValue = searchTextInElement(currentNode);
			//System.out.println(currentNode.getNodeName());
			if (!sNodeValue.trim().equalsIgnoreCase("")) {
				if(currentNode.getNodeName().equals("CDE")){

					while(sNodeValue.length()!=0){
						if(sNodeValue.contains(",")){
							hostExtractor(sNodeValue.substring(0,sNodeValue.indexOf(",")), this.getCDEHost());
							sNodeValue = sNodeValue.substring(sNodeValue.indexOf(",")+1, sNodeValue.length());
						}else{
							hostExtractor(sNodeValue, this.getCDEHost());
							sNodeValue = "";
						}
					}
				}
				if(currentNode.getNodeName().equals("ELEMENT")){
					String[] a=sNodeValue.split(",");
					ExposedElement elem = new ExposedElement(this.getCurrentID(), a[0], a[1], a[2], sNodeValue.substring(sNodeValue.lastIndexOf(",")+1, sNodeValue.length()));
					this.getExpEl().add(elem);
				}
				if(currentNode.getNodeName().equals("ID")){
					this.setCurrentID(sNodeValue);
				}
			}
		}

		int iChildNumber = currentNode.getChildNodes().getLength();
		if (currentNode.hasChildNodes()) {
			NodeList nlChilds = currentNode.getChildNodes();
			for (int iChild = 0; iChild < iChildNumber; iChild++) {
				printNodeInfo(nlChilds.item(iChild));
			}
		}
	}

	/**
	 * Search the content for a given Node
	 */
	private static String searchTextInElement(Node elementNode) {
		String sText = "";
		if (elementNode.hasChildNodes()) {
			Node nTextChild = elementNode.getChildNodes().item(0);
			sText = nTextChild.getNodeValue();
		}
		return sText;
	}

	private static void hostExtractor(String sNodeValue, List<String> hostlist) {

		if(sNodeValue.contains("-")){
			splitTargetRange(sNodeValue, hostlist);
		}else{
			boolean check = false;
			for(int k=0; k<hostlist.size();k++){
				if(hostlist.get(k).contains(sNodeValue)){
					check = true;
				}
			}
			if(check == false)
				hostlist.add(sNodeValue);
		}
	}

	private static void splitTargetRange(String hostAddress, List<String> hostlist) {

		String begin = hostAddress.substring(0, hostAddress.indexOf("-"));
		String end = hostAddress.substring(hostAddress.indexOf("-") + 1, hostAddress.length());
		int numberOfHostInRange = Integer.parseInt(end.substring(end.lastIndexOf(".") + 1, end.length())) - Integer.parseInt(begin.substring(begin.lastIndexOf(".") + 1, begin.length()));
		for(int i=0; i<=numberOfHostInRange; i++){
			Host host = new Host();
			host.setIPAddress(begin.substring(0,begin.lastIndexOf(".") + 1).concat(Integer.toString(Integer.parseInt(begin.substring(begin.lastIndexOf(".") + 1, begin.length()))+i)));

			boolean check = false;
			for(int k=0; k<hostlist.size();k++){
				if(hostlist.get(k).contains(host.getIPAddress()))
					check = true;
			}
			if(check == false)
				hostlist.add(host.getIPAddress());
		}
	}


	public List<String> getCDEHost() {
		return CDEHost;
	}

	public void setCDEHost(List<String> cDEHost) {
		CDEHost = cDEHost;
	}


	public List<ExposedElement> getExpEl() {
		return ExpEl;
	}


	public void setExpEl(List<ExposedElement> expEl) {
		ExpEl = expEl;
	}


	public String getCurrentID() {
		return currentID;
	}


	public void setCurrentID(String sNodeValue) {
		this.currentID = sNodeValue;
	}
}