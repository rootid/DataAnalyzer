package com.ub.dataanalyzer.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ub.dataanalyzer.client.data.Criteria;
import com.ub.dataanalyzer.shared.CountryArea;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryPopulation;

/**
 * This interface lists services provided to client.
 * @author vikram
 *
 */
@RemoteServiceRelativePath("clientqueryImpl")
public interface ClientServices extends com.google.gwt.user.client.rpc.RemoteService{
	
	
	
	//get the information from the server
	
//	public CountryCodeInfo getAllCountryCodes(String countryName);
	
	public String getCountryByLatLong(String lat, String longi);
	
	public List<String> getAllcountryNames();
	
	public List<CountryGeneralInfo> getAllcountryNamesList();
	
	public CountryCapitalMap getAllcountryCapitalMap(String countryName);
	
	public CountryCodeInfo getAllcountryCodes(String countryName);
	
	public List<String> getAllcountryNameCapitalMap ();
	
	public List<CountryGeneralInfo> getAllCountryCharInfo(Criteria criteria);
	
	public boolean isNeighboringcountry (String countryX,String countryY);
	
	public List<CountryGDP> getAllCountryGDPInfo(Criteria criteria);
	
	public List<CountryPopulation> getAllCountryPopulation(Criteria criteria);
	
	public List<CountryArea> getAllCountryArea(Criteria criteria);
	
	public List<CountryHDI> getAllCountryHDIInfo(Criteria criteria);
	
	public List<String> getAllNearestCapitalList(String iso3Code);
	
	public List<Double> getAllPopulationList();
	
	public List<CountryPopulation> getCountryListbyPopulation() ;
	
		
}
