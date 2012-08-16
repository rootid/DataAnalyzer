package com.ub.dataanalyzer.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ub.dataanalyzer.client.data.Criteria;
import com.ub.dataanalyzer.client.services.ClientServices;
import com.ub.dataanalyzer.shared.CountryArea;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryNeighborsInfo;
import com.ub.dataanalyzer.shared.CountryPopulation;
/**
 * This class implements services listed by the client part.
 * @author vikram
 *
 */
public class ClientServicesImpl extends RemoteServiceServlet 
implements ClientServices {

	private QueriesPerformer queriesPerformer;
	private boolean add;

	@Override
	public String getCountryByLatLong(String lat, String longi) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getAllcountryNames() {
		List<CountryGeneralInfo> genInfoList;
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		genInfoList = queriesPerformer.getAllCountryGenInfo();

		List<String> countryList = new ArrayList<String>();

		for(CountryGeneralInfo countryGenInfo:genInfoList) {
			countryList.add(countryGenInfo.getCountryName());
		}
		return countryList;
	}


	@Override
	public List<CountryGeneralInfo> getAllcountryNamesList() {
		List<CountryGeneralInfo> genInfoList = queriesPerformer.getAllCountryGenInfo();
		return genInfoList;
	}

	@Override
	public CountryCapitalMap getAllcountryCapitalMap(String countryName) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		CountryCapitalMap countryCapitalName = queriesPerformer.getcapitalAndMap(countryName);
		return countryCapitalName;
	}

	@Override
	public CountryCodeInfo getAllcountryCodes(String countryName) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		CountryCodeInfo countryCodeInfo = queriesPerformer.getCountryCodeInfo(countryName);
		return countryCodeInfo;
	}

	@Override
	public List<CountryGeneralInfo> getAllCountryCharInfo(Criteria criteria) {

		QueriesPerformer queriesPerformer = new QueriesPerformer();
		//List<CountryGeneralInfo> countryGenInfoList = queriesPerformer.getCountryListbyCriteria(criteria);

		return null;
	}

	@Override
	public List<String> getAllcountryNameCapitalMap() {
		List<String> countryNameList = new ArrayList<String>();
		QueriesPerformer queryPerformer = new QueriesPerformer();
		List<CountryCapitalMap> countryMapList = queryPerformer.getAllcapitalAndMap();
		for(CountryCapitalMap countryCapitalMap : countryMapList) {
			countryNameList.add(countryCapitalMap.getCountryName());
		}

		return countryNameList;
	}

	@Override
	public boolean isNeighboringcountry(String countryX, String countryY) {
		QueriesPerformer queryPerformer = new QueriesPerformer();
		boolean isnbr = false; 
		isnbr = queryPerformer.validCountryNebrs(countryX,countryY);
		return isnbr;
	}

	@Override
	public List<CountryGDP> getAllCountryGDPInfo(Criteria criteria) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		List<CountryGDP> countryGDPList = new ArrayList<CountryGDP>();
		List<CountryGDP> countryGDPTempList = queriesPerformer.getCountryListbyGDP(criteria);
		for(CountryGDP countryGDP : countryGDPTempList) {
			countryGDPList.add(countryGDP);
		}
		return countryGDPList;


	}

	@Override
	public List<CountryPopulation> getAllCountryPopulation(Criteria criteria) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		List<CountryPopulation> countryPopualationList = new ArrayList<CountryPopulation>();
		List<CountryPopulation>	countryPopulationTempList = queriesPerformer.getCountryListbyPopulation(criteria);	
		for(CountryPopulation countryPopulation : countryPopulationTempList) {
			countryPopualationList.add(countryPopulation);
		}
		return countryPopualationList;
	}

	@Override
	public List<CountryArea> getAllCountryArea(Criteria criteria) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		List<CountryArea> countryAreaList = new ArrayList<CountryArea>();
		List<CountryArea>	countryAreaTempList = queriesPerformer.getCountryListbyCountryArea(criteria);	
		for(CountryArea countryArea : countryAreaTempList) {
			countryAreaList.add(countryArea);
		}
		return countryAreaList;
	}

	@Override
	public List<CountryHDI> getAllCountryHDIInfo(Criteria criteria) {
		QueriesPerformer queriesPerformer = new QueriesPerformer();
		List<CountryHDI> countryHDITempList = queriesPerformer.getCountryListbyHDI(criteria);
		List<CountryHDI> countryHDIList = new ArrayList<CountryHDI>();	
		for(CountryHDI countryhdi : countryHDITempList) {
			countryHDIList.add(countryhdi);
		}
		return countryHDIList;
	}


	@Override
	public List<String> getAllNearestCapitalList(String iso3Code) {
		QueriesPerformer queryPerformer = new QueriesPerformer();
		List<String> countryList = new ArrayList<String>();
		List<String > tempList = queryPerformer.getNearestCapitalCities(iso3Code);
		for (String str : tempList) {
			countryList.add(str);
		}
		return countryList;
	}


	@Override
	public List<Double> getAllPopulationList() {
	
		QueriesPerformer querPerfomer = new QueriesPerformer();
		querPerfomer.getCountryListbyPopulation();
		return null;
	}


	@Override
	public List<CountryPopulation> getCountryListbyPopulation() {
		
		List<CountryPopulation> countryplist = new ArrayList<CountryPopulation>();
		
		QueriesPerformer querPerfomer = new QueriesPerformer();
		List<CountryPopulation> poplist =  querPerfomer.getCountryListbyPopulation();
		
		for(CountryPopulation country : poplist) {
			countryplist.add(country);
		}
		return countryplist;
	}	

	
}
