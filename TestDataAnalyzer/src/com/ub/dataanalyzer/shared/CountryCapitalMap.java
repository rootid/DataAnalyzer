package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country capital shared between client and server.
 * @author vikram
 *
 */
@PersistenceCapable
public class CountryCapitalMap implements Serializable {
	@Persistent
	private String countryName;
	@PrimaryKey
	@Persistent
	private String iso3;
	@Persistent
	private String capitalName;
	@Persistent
	private String ContinentName;
	@Persistent
	private String mapUrl;
	
	public CountryCapitalMap() {
	
	}

	public CountryCapitalMap(String countryName, String iso3,
			String capitalName, String continentName, String mapUrl) {
		super();
		this.countryName = countryName;
		this.iso3 = iso3;
		this.capitalName = capitalName;
		ContinentName = continentName;
		this.mapUrl = mapUrl;
	}



	public String getMapUrl() {
		return mapUrl;
	}



	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}



	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public String getContinentName() {
		return ContinentName;
	}

	public void setContinentName(String continentName) {
		ContinentName = continentName;
	}
	
}
