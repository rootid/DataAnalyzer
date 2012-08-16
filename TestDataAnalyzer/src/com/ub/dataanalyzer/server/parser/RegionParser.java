package com.ub.dataanalyzer.server.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class performs parsing to extract information about region.
 * @author vikram
 *
 */
public class RegionParser extends Parser{

	private String regionName = "";
	
	public RegionParser(String inputResponse) {
		super(inputResponse);
		this.inputStream = new ByteArrayInputStream(inputResponse.getBytes());;
	}

	/**
	 *  Parse the xml response with respect to the keys
	 * @param keys
	 */
	@Override
	public void parseXml(String[] keys) {
		
	}
	
	@Override
	public void parseXml() {
		saxParserInstance =  SAXParserFactory.newInstance();
		objectMap = new HashMap<Object, List<Map<String,String>>>();
		objectList = new ArrayList<Map<String,String>>();
		mapObject = new HashMap<String, String>();
		try {
			saxParser = saxParserInstance.newSAXParser();
			saxParser.parse(this.inputStream, new XmlParserHandler());					
		} catch (ParserConfigurationException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			//clear the database
			clearAllDatabase();
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				

	
	}


	public Map<Object, List<Map<String, String>>> getObjectMap() {
		return objectMap;
	}
	
	private void clearAllDatabase () {
		objectMap = null;
	}


	class XmlParserHandler extends DefaultHandler {

		public XmlParserHandler() {

		}

		public void startDocument() 
				throws SAXException {
			System.out.println("Parsing of document started");
		}

		public void endDocument()
				throws SAXException {			
			System.out.println("Parsing of document ended");
			objectMap.put("0001", objectList);
		}


		public void startElement(String namespaceURI, String localName, 
				String qName, Attributes atts)throws SAXException {
			if(qName.equalsIgnoreCase("region")) {
				if(atts.getLength()!=0) {
					regionName = atts.getValue("name");
				}	    
			}	
			if(qName.equalsIgnoreCase("codeISO3")) {
				countForObject++;
				elementName = "codeISO3";
				found = true;
			}	
		}
	

		/** Add object to map
		 * 
		 */
		private void addObjectToMap() {

			Map<String,String>localMapObj = new HashMap<String, String>();
			localMapObj.putAll(mapObject);
			objectList.add(localMapObj);
			mapObject.clear();
			countForObject = 1; //Should be 1 else we get unacceptable results.

		}

		public void endElement(String namespaceURI, String localName, 
				String qName)throws SAXException {
			found = false;
		}


		public void characters(char[] ch,int start,int length)
				throws SAXException {
			if(found == true ) {
					String element = new String(ch, start , length);
					mapObject.put(elementName, element);
					mapObject.put("region",regionName);
					found = false;
					if (countForObject == 2) {
						addObjectToMap();
					}
				}
		}	

	}

}
