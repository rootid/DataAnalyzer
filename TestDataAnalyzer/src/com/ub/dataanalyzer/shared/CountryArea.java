package com.ub.dataanalyzer.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * This class is used to store the country area information shared between client and server.
 * 
 * @author vikram
 *
 */
@PersistenceCapable
public class CountryArea implements Serializable {
	@Persistent
	private String countryName;
	@PrimaryKey
	@Persistent
	private String iso3Code;
	@Persistent
	private String year;
	@Persistent
	private Double size;
	@Persistent
	private String estimation_type;
	@Persistent
	private String unit;
	@Persistent
	private String countryAreaType;
	@Persistent
	private String sourceInformation;



	public CountryArea() {

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



	public String getEstimation_type() {
		return estimation_type;
	}



	public void setEstimation_type(String estimation_type) {
		this.estimation_type = estimation_type;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getCountryAreaType() {
		return countryAreaType;
	}

	public void setCountryAreaType(String countryAreaType) {
		this.countryAreaType = countryAreaType;
	}

	public String getSourceInformation() {
		return sourceInformation;
	}


	public void setSourceInformation(String sourceInformation) {
		this.sourceInformation = sourceInformation;
	}


	



}
