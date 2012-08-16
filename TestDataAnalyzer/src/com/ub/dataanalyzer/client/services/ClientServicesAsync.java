package com.ub.dataanalyzer.client.services;

import java.util.List;

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
 * This interface lists async part of services provided to client.
 * @author vikram
 *
 */
public interface ClientServicesAsync {

//	void getAllCountryCodes(String countryName,
//			AsyncCallback<CountryCodeInfo> callback);

	void getCountryByLatLong(String lat, String longi,
			AsyncCallback<String> callback);

//	void getcountryNamesbyCharInfo(CountryCharInfo countryCharInfo,
//			AsyncCallback<List<String>> callback);


	void getAllcountryNames(AsyncCallback<List<String>> callback);

	void getAllcountryNamesList(AsyncCallback<List<CountryGeneralInfo>> callback);

	void getAllcountryCapitalMap(String countryName,
			AsyncCallback<CountryCapitalMap> callback);

	void getAllcountryCodes(String countryName,
			AsyncCallback<CountryCodeInfo> callback);

	void getAllCountryCharInfo(Criteria criteria,
			AsyncCallback<List<CountryGeneralInfo>> callback);

	void getAllcountryNameCapitalMap(AsyncCallback<List<String>> callback);

	void isNeighboringcountry(String countryX, String countryY,
			AsyncCallback<Boolean> callback);

	void getAllCountryGDPInfo(Criteria criteria,
			AsyncCallback<List<CountryGDP>> callback);

	void getAllCountryPopulation(Criteria criteria,
			AsyncCallback<List<CountryPopulation>> callback);

	void getAllCountryArea(Criteria criteria,
			AsyncCallback<List<CountryArea>> callback);

	void getAllCountryHDIInfo(Criteria criteria,
			AsyncCallback<List<CountryHDI>> callback);

	void getAllNearestCapitalList(String iso3Code,
			AsyncCallback<List<String>> callback);

	void getAllPopulationList(AsyncCallback<List<Double>> callback);

	void getCountryListbyPopulation(
			AsyncCallback<List<CountryPopulation>> callback);




}
