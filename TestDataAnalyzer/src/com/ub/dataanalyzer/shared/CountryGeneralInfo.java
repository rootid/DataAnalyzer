package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the general information about client country shared between client and server. 
 * @author vikram
 *
 */
@PersistenceCapable
public class CountryGeneralInfo implements Serializable {

	@Persistent
	private String countryName;
	@PrimaryKey
	@Persistent
	private String ISO3;


	public CountryGeneralInfo() {

	}

	public CountryGeneralInfo(String countryName, String iSO3) {
		super();
		this.countryName = countryName;
		this.ISO3 = iSO3;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getISO3() {
		return ISO3;
	}


	public void setISO3(String iSO3) {
		ISO3 = iSO3;
	}






}
