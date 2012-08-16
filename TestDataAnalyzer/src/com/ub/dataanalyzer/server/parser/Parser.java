package com.ub.dataanalyzer.server.parser;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * This class acts as base class for all parsers. 
 * @author vikram
 *
 */
public abstract class Parser {

	SAXParserFactory saxParserInstance; 
	SAXParser saxParser;
	InputStream inputStream;
	String[] keys;
	boolean found = false;
	String elementName;
	String fileName;
	int countForObject;
	Map<Object, List<Map<String, String>>> objectMap;
	List<Map<String,String>> objectList;
	Map<String, String> mapObject;

	public Parser() {

	}	
	public Parser (String reponse) {

	}
	public abstract void parseXml();

	public abstract void parseXml(String[] keys);

	public abstract Map<Object, List<Map<String, String>>> getObjectMap();

}
