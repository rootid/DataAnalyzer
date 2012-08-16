package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country GDP information shared between client and server.
 * 
 * @author vikram
 *
 */
@PersistenceCapable
public class CountryGDP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent
	private String estimationType;
	@Persistent
	private String unitType;
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
	private double value;

	public CountryGDP() {

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
	
	public String getEstimationType() {
		return estimationType;
	}
	
	public void setEstimationType(String estimationType) {
		this.estimationType = estimationType;
	}
	
	public String getUnitType() {
		return unitType;
	}
	
	public void setUnitType(String unitType) {
		this.unitType = unitType;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}



}
