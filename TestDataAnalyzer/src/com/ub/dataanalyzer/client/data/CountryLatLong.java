package com.ub.dataanalyzer.client.data;

import java.io.Serializable;

public class CountryLatLong implements Serializable{
	
	private String Lat;
	private String Long;
	
	CountryLatLong() {

	}

	public CountryLatLong(String lat, String l) {
		super();
		Lat = lat;
		Long = l;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

//	@Override
//	public String doExport() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String doImport() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	

}
