package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the various codes of country shared between client and server.
 * @author vikram
 *
 */

@PersistenceCapable
public class CountryCodeInfo implements Serializable{

	@PrimaryKey
	@Persistent
	private String iso3code;
	@Persistent
	private String countryName;
	@Persistent
	private String country_DBPEDIA_ID;
	@Persistent
	private String country_AGROVOC;
	@Persistent
	private String country_GAUL;
	@Persistent
	private String country_FAOTERM;
	@Persistent
	private String country_FAOSTAT;
	@Persistent
	private String country_UNDP;
	@Persistent
	private String country_UN;
	
	public CountryCodeInfo() {
		super();
	}
	

	public String getIso3code() {
		return iso3code;
	}


	public void setIso3code(String iso3code) {
		this.iso3code = iso3code;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}





	public String getCountry_DBPEDIA_ID() {
		return country_DBPEDIA_ID;
	}

	public void setCountry_DBPEDIA_ID(String country_DBPEDIA_ID) {
		this.country_DBPEDIA_ID = country_DBPEDIA_ID;
	}

	public String getCountry_AGROVOC() {
		return country_AGROVOC;
	}

	public void setCountry_AGROVOC(String country_AGROVOC) {
		this.country_AGROVOC = country_AGROVOC;
	}

	public String getCountry_GAUL() {
		return country_GAUL;
	}

	public void setCountry_GAUL(String country_GAUL) {
		this.country_GAUL = country_GAUL;
	}

	public String getCountry_FAOTERM() {
		return country_FAOTERM;
	}

	public void setCountry_FAOTERM(String country_FAOTERM) {
		this.country_FAOTERM = country_FAOTERM;
	}

	public String getCountry_FAOSTAT() {
		return country_FAOSTAT;
	}

	public void setCountry_FAOSTAT(String country_FAOSTAT) {
		this.country_FAOSTAT = country_FAOSTAT;
	}

	public String getCountry_UNDP() {
		return country_UNDP;
	}

	public void setCountry_UNDP(String country_UNDP) {
		this.country_UNDP = country_UNDP;
	}

	public String getCountry_UN() {
		return country_UN;
	}

	public void setCountry_UN(String country_UN) {
		this.country_UN = country_UN;
	}
	
}
