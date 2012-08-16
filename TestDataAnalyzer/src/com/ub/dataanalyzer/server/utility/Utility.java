package com.ub.dataanalyzer.server.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.ub.dataanalyzer.server.manager.PMF;

public final class Utility {

	public static Properties tpsProperties;
	public static Properties dbProperties;
	public static String filePath;

	public static String[] splitString(String src) {
		String[] stringList = src.split(",");
		return stringList;
	}

	public static String[] populateAllServices() {
		String [] service = null;
		return service;
	}

	public static List<String> splitHashString(String src) {
		String[] stringArray = src.split("#");
		List<String>stringList = Arrays.asList(stringArray);
		return stringList;
	}

	public static void createDataBaseClass(String key) {

	}

	/**
	 * 
	 */
	public static void initializeKey(String filePath) {
		//Get all the list of urls
		KeyGenerator keyGenerator = new KeyGenerator
				(filePath, "ThirdPartyServices.properties");
		//tpsKeyList = keyGenerator.getAllKeys();
		tpsProperties = keyGenerator.getProperties();


		//Get all the list of attributes
		KeyGenerator keyGenerator2 = new KeyGenerator
				(filePath, "Database.properties");
		//dbKeyList = keyGenerator2.getAllKeys();
		dbProperties = keyGenerator2.getProperties();
	}

	public static String getFilePath() {
		return filePath;
	}

	public static void setFilePath(String filePath) {
		Utility.filePath = filePath;
	}

	/**
	 * Haversion formaula for distance calculation
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double haversion_dist(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double dist = earthRadius * c;

		return dist;
	}

}
