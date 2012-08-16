package com.ub.dataanalyzer.server.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This class performs parsing to extract information about capital.
 * @author vikram
 *
 */
public class CapitalParser extends Parser {


	public CapitalParser(String inputResponse) {
		this.inputStream = new ByteArrayInputStream(inputResponse.getBytes());	
		//this.fileName = inputResponse;
	}
	

	@Override
	public void parseXml() {
		
	}

	/**
	 *  Parse the xml response with respect to the keys
	 * @param keys
	 */
	@Override
	public void parseXml(String[] keys) {

		this.keys = keys;
		this.countForObject = 0;
		saxParserInstance =  SAXParserFactory.newInstance();
		//saxParserInstance.setValidating(true);
	
		objectMap = new HashMap<Object, List<Map<String,String>>>();
		objectList = new ArrayList<Map<String,String>>();
		mapObject = new HashMap<String, String>();
		try {
//			The SAX parser uses InputSource internally and 
//			from the InputSource docs:
//			The SAX parser will use the InputSource object to 
//			determine how to read XML input. If there is a character 
//			stream available, the parser will read that stream directly, 
//			disregarding any text encoding declaration found in that stream. 
//			If there is no character stream, but there is a byte stream, 
//			the parser will use that byte stream, using the encoding specified in 
//			the InputSource or else (if no encoding is specified) autodetecting 
//			the character encoding using an algorithm such as the one in the XML 
//			specification. If neither a character stream nor a byte stream 
//			is available, the parser will attempt to open a URI connection 
//			to the resource identified by the system identifier.
//			So basically you need to pass a character stream to the parser 
//			for it to pick-up the correct encoding. 
			
			saxParser = saxParserInstance.newSAXParser();
			Reader isr = new InputStreamReader(this.inputStream);
			InputSource is = new InputSource();
			is.setCharacterStream(isr);
			saxParser.parse(is, new XmlParserHandler());	
			//saxParser.parse(new File(fileName), new XmlParserHandler());
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


	class XmlParserHandler extends DefaultHandler implements ErrorHandler {

		int parenthesis = 0;
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

		@Override
		public void startElement(String namespaceURI, String localName, 
				String qName, Attributes atts)throws SAXException {
			for (String str : keys) {
				if(str.equalsIgnoreCase(qName)) {
					countForObject++;
					parenthesis ++;
					elementName = str;
					found = true;
					break;
				}

			}
		}


		/** Add object to map
		 * 
		 */
		private void addObjectToMap() {			
			if(countForObject == keys.length) {
			Map<String,String>localMapObj = new HashMap<String, String>();
			localMapObj.putAll(mapObject);
			objectList.add(localMapObj);
			mapObject.clear();
			countForObject = 0; //Should be 1 else we get unacceptable results.
			}

		}

		@Override
		public void endElement(String namespaceURI, String localName, 
				String qName)throws SAXException {
//			found = false;
			if(parenthesis == 0) {
				
			}
			else {
				mapObject.put(elementName, "No data");
				found = false;
				parenthesis = 0;
				addObjectToMap();
			}
		}


		public void characters(char[] ch,int start,int length)
				throws SAXException {
			if(found == true && parenthesis==1) {
				String element = new String(ch, start , length);
				//System.out.println("Element name :"+elementName+"Element :"+element);
				mapObject.put(elementName, element);
				found = false;
				parenthesis--;
				addObjectToMap();
			}	

		}	
		
		@Override
		public void fatalError(SAXParseException exception)
                 {
			System.out.println("fatal error get callled");
		}
		
		public void error(SAXParseException exception)
		{
			System.out.println("error get called");
			
		}
		
		public void warning(SAXParseException exception)
	             {
			System.out.println("warning get callled");
		}
		
		

	}

	
}
