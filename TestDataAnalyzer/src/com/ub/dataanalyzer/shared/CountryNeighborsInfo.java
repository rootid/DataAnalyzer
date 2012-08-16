package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country neighbors information shared between client and server.
 * @author vikram
 *
 */

@PersistenceCapable
public class CountryNeighborsInfo implements Serializable{


	@Persistent
	private String neighborCountry_name;
	@Persistent
	private String neighborCountry_iso3;
	@Persistent
	private String iso3;
	@PrimaryKey
	@Persistent
	private String countryName;

	public CountryNeighborsInfo() {
	}


	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getNeighborCountry_name() {
		return neighborCountry_name;
	}

	public void setNeighborCountry_name(String neighborCountry_name) {
		this.neighborCountry_name = neighborCountry_name;
	}

	public String getNeighborCountry_iso3() {
		return neighborCountry_iso3;
	}

	public void setNeighborCountry_iso3(String neighborCountry_iso3) {
		this.neighborCountry_iso3 = neighborCountry_iso3;
	}

}

