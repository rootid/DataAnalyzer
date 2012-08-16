package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country population shared between client and server.
 * @author vikram
 *
 */
@PersistenceCapable
public class CountryPopulation implements Serializable {
	
	@Persistent
	private String unit;
	@Persistent
	private String estimationType;
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
	
	public CountryPopulation() {
	
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



	public double getValue() {
		return value;
	}



	public void setValue(double value) {
		this.value = value;
	}



	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getEstimationType() {
		return estimationType;
	}


	public void setEstimationType(String estimationType) {
		this.estimationType = estimationType;
	}
	
	
}
