package com.ub.dataanalyzer.client.queryengine.view;

import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

public class CountryISO3Parser {
	
	private String countryIso3;
	
	public CountryISO3Parser(String messageXml) {
		parseXML(messageXml);
	}

	private void parseXML(String messageXml) {
		try {
			// parse the XML document into a DOM
			Document xmlDom =  XMLParser.parse(messageXml);
			// get the message body by explicitly casting to a Text node
			String ciso3 = xmlDom.getElementsByTagName("isoAlpha3").item(0).getFirstChild().getNodeValue();
			setCountryIso3(ciso3);

		} catch (DOMException e) {
			Window.alert("Could not parse XML document.");
		}
	}

	public String getCountryIso3() {
		return countryIso3;
	}

	public void setCountryIso3(String countryIso3) {
		this.countryIso3 = countryIso3;
	}
	

}
