package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country Human Development Index shared between client and server.
 * @author vikram
 *
 */
@PersistenceCapable	
public class CountryHDI implements Serializable{
	
	@Persistent
	private String estimation_type;
	@Persistent
	private String countryName;
	@PrimaryKey
	@Persistent
	private String iso3Code;
	@Persistent
	private String year;
	@Persistent
	private String source;
	@Persistent
	private Double value;

	public CountryHDI() {

	}
	
	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public String getIso3Code() {
		return iso3Code;
	}


	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	public String getEstimation_type() {
		return estimation_type;
	}
	public void setEstimation_type(String estimation_type) {
		this.estimation_type = estimation_type;
	}

}
