package com.ub.dataanalyzer.server.pojo;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class CountryAgriArea {
	@Persistent
	private String countryName;
	@PrimaryKey
	@Persistent
	private String iso3Code;
	@Persistent
	private String year;
	@Persistent
	private String size;
	@Persistent
	private String estimation_type;
	@Persistent
	private String unit;
	@Persistent
	private String countryAreaType;
	@Persistent
	private String sourceInformation;



	public CountryAgriArea() {

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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
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
