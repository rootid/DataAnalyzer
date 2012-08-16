package com.ub.dataanalyzer.client.services;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ub.dataanalyzer.client.data.Criteria;
import com.ub.dataanalyzer.shared.CountryArea;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryPopulation;

/**
 * This class invokes services provided by client.
 * @author vikram
 *
 */
public class DataBroker implements ClientServicesAsync {
	
	
	private static final ClientServicesAsync dataservice = 
			GWT.create(ClientServices.class); //(service proxy class)
	private static DataBroker instance;
	
	private DataBroker() {
		
	}	

	public static DataBroker getInstance() {
		if (instance == null) {
			instance = new DataBroker();
		}
		return instance;
	}


//	@Override
//	public void getAllCountryCodes(String countryName,
//			AsyncCallback<CountryCodeInfo> callback) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void getCountryByLatLong(String lat, String longi,
			AsyncCallback<String> callback) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void getcountryNamesbyCharInfo(CountryCharInfo countryCharInfo,
//			AsyncCallback<List<String>> callback) {
//		dataservice.getcountryNamesbyCharInfo(countryCharInfo, callback);
//		
//	}


	@Override
	public void getAllcountryNames(AsyncCallback<List<String>> callback) {
		dataservice.getAllcountryNames(callback);
		
	}

	@Override
	public void getAllcountryNamesList(
			AsyncCallback<List<CountryGeneralInfo>> callback) {
		
		dataservice.getAllcountryNamesList(callback);
	}

	@Override
	public void getAllcountryCapitalMap(String countryName,
			AsyncCallback<CountryCapitalMap> callback) {
		dataservice.getAllcountryCapitalMap(countryName, callback);
		
	}

	@Override
	public void getAllcountryCodes(String countryName,
			AsyncCallback<CountryCodeInfo> callback) {
		dataservice.getAllcountryCodes(countryName, callback);
		
	}

	/**
	 * 
	 */
	@Override
	public void getAllCountryCharInfo(Criteria criteria,
			AsyncCallback<List<CountryGeneralInfo>> callback) {
		dataservice.getAllCountryCharInfo(criteria, callback);
		
	}

	/**
	 * 
	 */
	@Override
	public void getAllcountryNameCapitalMap(AsyncCallback<List<String>> callback) {
		dataservice.getAllcountryNameCapitalMap(callback);
		
	}

	@Override
	public void isNeighboringcountry(String countryX, String countryY,
			AsyncCallback<Boolean> callback) {
		dataservice.isNeighboringcountry(countryX, countryY, callback);
		
	}

	@Override
	public void getAllCountryGDPInfo(Criteria criteria,
			AsyncCallback<List<CountryGDP>> callback) {
		dataservice.getAllCountryGDPInfo(criteria, callback);
		
	}

	@Override
	public void getAllCountryPopulation(Criteria criteria,
			AsyncCallback<List<CountryPopulation>> callback) {
		dataservice.getAllCountryPopulation(criteria, callback);
		
	}

	@Override
	public void getAllCountryArea(Criteria criteria,
			AsyncCallback<List<CountryArea>> callback) {
		dataservice.getAllCountryArea(criteria, callback);
		
	}

	@Override
	public void getAllCountryHDIInfo(Criteria criteria,
			AsyncCallback<List<CountryHDI>> callback) {
		dataservice.getAllCountryHDIInfo(criteria, callback);
		
	}

	@Override
	public void getAllNearestCapitalList(String iso3Code,
			AsyncCallback<List<String>> callback) {
		dataservice.getAllNearestCapitalList(iso3Code, callback);
		
	}

	@Override
	public void getAllPopulationList(AsyncCallback<List<Double>> callback) {
		dataservice.getAllPopulationList(callback);
		
	}

	@Override
	public void getCountryListbyPopulation(
			AsyncCallback<List<CountryPopulation>> callback) {
		dataservice.getCountryListbyPopulation(callback);
	}
	
}
