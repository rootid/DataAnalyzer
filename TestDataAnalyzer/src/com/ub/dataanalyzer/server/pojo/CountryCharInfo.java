package com.ub.dataanalyzer.server.pojo;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class CountryCharInfo{

	private String year;
	private String source;
	private String value;
	
	public CountryCharInfo() {
		
	}
	
	public CountryCharInfo(String year, String source, String value) {
		super();
		this.year = year;
		this.source = source;
		this.value = value;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
