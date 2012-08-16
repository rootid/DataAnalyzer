package com.ub.dataanalyzer.server.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CountryCoOrdinate {
	@PrimaryKey
	@Persistent
	private String iso3Code;
	@Persistent
	private String countryName;
	@Persistent
	private String minLat;
	@Persistent
	private String minLong;
	@Persistent
	private String maxLat;
	@Persistent
	private String maxLong;

	public CountryCoOrdinate() {

	}

	
	public String getIso3Code() {
		return iso3Code;
	}


	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}

	public String getCountryName() {
		return countryName;
	}



	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}



	public String getMinLat() {
		return minLat;
	}

	public void setMinLat(String minLat) {
		this.minLat = minLat;
	}

	public String getMinLong() {
		return minLong;
	}

	public void setMinLong(String minLong) {
		this.minLong = minLong;
	}

	public String getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(String maxLat) {
		this.maxLat = maxLat;
	}

	public String getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(String maxLong) {
		this.maxLong = maxLong;
	}



}
