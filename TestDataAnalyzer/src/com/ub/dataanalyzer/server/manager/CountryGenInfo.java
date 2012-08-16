package com.ub.dataanalyzer.server.manager;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CountryGenInfo {
	@PrimaryKey
	@Persistent
	private String countryName;
	@Persistent
	private String iso3_code;
	
	
	
	public CountryGenInfo(String countryName, String iso3_code) {
		super();
		this.countryName = countryName;
		this.iso3_code = iso3_code;
	}
	
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getIso3_code() {
		return iso3_code;
	}
	public void setIso3_code(String iso3_code) {
		this.iso3_code = iso3_code;
	}
	
	
}
