package com.ub.dataanalyzer.server.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CountryRegionInfo {
	
	@PrimaryKey
	@Persistent
	private String iso3Code;
	@Persistent
	private String regionName;

	public CountryRegionInfo() {
		
	}	
		

	public String getIso3Code() {
		return iso3Code;
	}


	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}



	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
	
	

}
