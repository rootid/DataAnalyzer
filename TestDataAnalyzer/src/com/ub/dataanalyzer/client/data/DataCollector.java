package com.ub.dataanalyzer.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.client.data.Criteria.CriteriaType;
import com.ub.dataanalyzer.client.services.DataBroker;

public class DataCollector {

	//singleton as we have to call the populate only once in application lifetime
	private DataBroker dataBroker;
	private static List<String> countryNameList = null;
	private static List<CountryGeneralInfo> countryGenInfoList = null;
	private static DataCollector dataCollectorInstance = null;

	/**
	 * 
	 * @return
	 */
	public static DataCollector getInstance() {
		if(dataCollectorInstance == null) {
			dataCollectorInstance = new DataCollector();
		} 
		return dataCollectorInstance;
	}

	/**
	 * 
	 */
	private DataCollector() {
		dataBroker = DataBroker.getInstance();
	}

	/**
	 * 
	 */
	public void populateCountryGenInfoList() {
		if(countryGenInfoList == null) {
			return;
		}
		countryGenInfoList = new ArrayList<CountryGeneralInfo>();
		dataBroker.getAllcountryNamesList(new AsyncCallback<List<CountryGeneralInfo>>() {
			
			@Override
			public void onSuccess(List<CountryGeneralInfo> result) {
				for(CountryGeneralInfo countryInfo : result) {
					countryGenInfoList.add(countryInfo);
				}	
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Exception is fired");
				caught.printStackTrace();	
			}
		});
	}
	
	/**
	 * 
	 */
	public void populateCountryNameList() {
		if(countryNameList != null) {
			return;
		}
		countryNameList = new ArrayList<String>();
		dataBroker.getAllcountryNames(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for(String str : result) {
					countryNameList.add(str);
				}			
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Exception is fired");
				caught.printStackTrace();
			}
		});

	}

	/**
	 * 
	 * @return
	 */
	public DataBroker getDataBroker() {
		return dataBroker;
	}

	/**
	 * 
	 * @return
	 */
	public static List<String> getCountryNameList() {
		return countryNameList;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<CountryGeneralInfo> getCountryGenInfoList() {
		return countryGenInfoList;
	}

	/**
	 * 
	 * @param countryName
	 * @return
	 */
	public CountryCapitalMap getMapandCapital(String countryName) {
		
		 final CountryCapitalMap countryMapCapital = new CountryCapitalMap();
		
		dataBroker.getAllcountryCapitalMap(countryName, new 
				AsyncCallback<CountryCapitalMap>() {
			
			@Override
			public void onSuccess(CountryCapitalMap result) {
				System.out.println("Got the result object");
				countryMapCapital.setCapitalName(result.getCapitalName());
				countryMapCapital.setMapUrl(result.getMapUrl());
				countryMapCapital.setContinentName(result.getContinentName());
				countryMapCapital.setIso3(result.getIso3());
				countryMapCapital.setCountryName(result.getCountryName());
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}
		});
		
		return countryMapCapital;
		
	}
	
	
	public List<String> getCountriesbyCriteria() {
		
		final List<String> countries = new ArrayList<String>();
		
		Criteria criteria = new Criteria();
		criteria.setCriteriaType(CriteriaType.TOP);
		criteria.setNumber(10);
		criteria.setParameter("GDP");
		
		dataBroker.getAllCountryCharInfo(criteria, new  AsyncCallback<List<CountryGeneralInfo>>() {
			
			@Override
			public void onSuccess(List<CountryGeneralInfo> result) {
				System.out.println("The result size" +result.size());
				for (CountryGeneralInfo countryInfo : result) {
					countries.add(countryInfo.getCountryName());
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		return countries;
		
	}
	
	
	public List<String> getCountriesbyGDPCriteria() {
		
		final List<String> countries = new ArrayList<String>();
		
		Criteria criteria = new Criteria();
		criteria.setCriteriaType(CriteriaType.TOP);
		criteria.setNumber(10);
		criteria.setParameter("GDP");
	
		dataBroker.getAllCountryGDPInfo(criteria, new AsyncCallback<List<CountryGDP>>() {
			
			@Override
			public void onSuccess(List<CountryGDP> result) {
				System.out.println("The result size" +result.size());
				for (CountryGDP countryInfo : result) {
					countries.add(countryInfo.getCountryName());
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return countries;
		
	}
}
