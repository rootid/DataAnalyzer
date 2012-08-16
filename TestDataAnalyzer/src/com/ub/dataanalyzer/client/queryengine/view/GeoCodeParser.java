package com.ub.dataanalyzer.client.queryengine.view;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;

import com.google.gwt.xml.client.XMLParser;



public class GeoCodeParser {
	
	private String countryName;
	private String countryIso2;
	
	public GeoCodeParser(String messageXml) {
		parseXML(messageXml);
	}

	private void parseXML(String messageXml) {
		try {
			// parse the XML document into a DOM
			Document xmlDom =  XMLParser.parse(messageXml);
			// get the message body by explicitly casting to a Text node
			String cname = xmlDom.getElementsByTagName("countryName").item(0).getFirstChild().getNodeValue();
			setCountryName(cname);
			String iso2 = xmlDom.getElementsByTagName("countryCode").item(0).getFirstChild().getNodeValue();
			setCountryIso2(iso2);

		} catch (DOMException e) {
			Window.alert("Could not parse XML document.");
		}
	}
	
	
	public String getCountryIso2() {
		return countryIso2;
	}

	public void setCountryIso2(String countryIso2) {
		this.countryIso2 = countryIso2;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
}
