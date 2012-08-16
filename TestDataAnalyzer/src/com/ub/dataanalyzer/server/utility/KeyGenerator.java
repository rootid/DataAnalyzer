package com.ub.dataanalyzer.server.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class KeyGenerator {
	
	private String fileName;
	private List<String> keyList;
	private Properties properties = null;

	public KeyGenerator(String filePath,String fileName) {

		properties = new Properties();	
		 try {
			properties.load(new FileInputStream(filePath+"/"+fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	public Properties getProperties() {
		return properties;
		
	}
	
	/**
	 * 
	 * @return List<String> - returns all the Keys associated with 
	 * 						  list.
	 */
	public List<String> getAllKeys() {
		keyList = new ArrayList<String>();
		Set<Object> keySet = new HashSet<Object>();
		keySet = properties.keySet();
		for (Object object : keySet) {
			keyList.add((String)object);
		}

		return keyList;
	}
		
}
