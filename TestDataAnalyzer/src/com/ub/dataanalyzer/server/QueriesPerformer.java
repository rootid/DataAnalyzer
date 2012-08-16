package com.ub.dataanalyzer.server;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.ub.dataanalyzer.client.data.Criteria;
import com.ub.dataanalyzer.server.manager.PMF;
import com.ub.dataanalyzer.server.pojo.CountryRegionInfo;
import com.ub.dataanalyzer.server.utility.Utility;
import com.ub.dataanalyzer.shared.CountryArea;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryNeighborsInfo;
import com.ub.dataanalyzer.shared.CountryPopulation;

/**
 * This class performs query operation.
 * @author vikram
 *
 */
public class QueriesPerformer {
	Query query;
	private PersistenceManager pm;


	public QueriesPerformer() {
		pm = PMF.get().getPersistenceManager();
	}

	/**
	 * 
	 * @return
	 */
	public List<CountryCapitalMap> getAllcapitalAndMap() {

		query =  pm.newQuery(CountryCapitalMap.class);

		List<CountryCapitalMap> results;
		try {
			results = (List<CountryCapitalMap>) query.execute();

		} finally {
			query.closeAll();
		}
		return results;

	}

	/**
	 * 
	 * @return
	 */
	public CountryCapitalMap getcapitalAndMap(String countryName) {

		List<CountryCapitalMap> countryCapitalMapList;
		query =  pm.newQuery(CountryCapitalMap.class,"countryName == paraCountryName");
		// declare params used above
		query.declareParameters("String paraCountryName"); 

		//pass the object declared as params
		try {
			countryCapitalMapList = (List<CountryCapitalMap>) query.execute(countryName);	
		} finally  {
			query.closeAll();	
		}

		if (countryCapitalMapList == null || countryCapitalMapList.size() == 0)
			return null;
		else
			return countryCapitalMapList.get(0);

	}


	/**
	 * 
	 * @return
	 */
	public CountryCapitalMap getcapitalAndMapbyISO3(String iso3Code) {

		List<CountryCapitalMap> countryCapitalMapList;
		query =  pm.newQuery(CountryCapitalMap.class,"iso3 == paraCountryiso3");
		// declare params used above
		query.declareParameters("String paraCountryiso3"); 

		//pass the object declared as params
		try {
			countryCapitalMapList = (List<CountryCapitalMap>) query.execute(iso3Code);	
		} finally  {
			query.closeAll();	
		}

		if (countryCapitalMapList == null || countryCapitalMapList.size() == 0)
			return null;
		else
			return countryCapitalMapList.get(0);

	}

	/**
	 * Get all Objects of CountryGeneralInfo
	 * @return
	 */
	public List<CountryGeneralInfo> getAllCountryGenInfo() {
		query =  pm.newQuery(CountryGeneralInfo.class);
		List<CountryGeneralInfo> countryGenInfoList;
		try {
			countryGenInfoList = (List<CountryGeneralInfo>) query.execute();

		} finally {
			query.closeAll();
		}
		return countryGenInfoList;

	}

	/**
	 * Get all region info
	 * @return
	 */
	public List<CountryRegionInfo> getAllRegionInfo() {
		query = pm.newQuery(CountryRegionInfo.class);
		List<CountryRegionInfo> countryRegionList;
		try {
			countryRegionList = (List<CountryRegionInfo>) query.execute();

		} finally {
			query.closeAll();
		}
		return countryRegionList;
	}


	/**
	 * 
	 * @param countryName
	 * @return
	 */
	public CountryCodeInfo getCountryCodeInfo(String countryName) {
		List<CountryCodeInfo> countryCodeInfoList;
		query =  pm.newQuery(CountryCodeInfo.class,"countryName == paraCountryName");
		// declare params used above
		query.declareParameters("String paraCountryName");

		try {
			countryCodeInfoList = (List<CountryCodeInfo>) query.execute(countryName);	
		} finally  {
			query.closeAll();	
		}
		if (countryCodeInfoList == null || countryCodeInfoList.size() == 0)
			return null;
		else
			return countryCodeInfoList.get(0);
	}

	/**
	 * 
	 * @return
	 */
	public CountryNeighborsInfo getNbrCountries(String countryiso3) {
		List<CountryNeighborsInfo> countryNeighborList;

		query =  pm.newQuery(CountryNeighborsInfo.class,"iso3 == paraCountryiso3");
		// declare params used above
		query.declareParameters("String paraCountryiso3");
		try {
			countryNeighborList = (List<CountryNeighborsInfo>) query.execute(countryiso3);	
		} finally  {
			query.closeAll();	
		}
		if (countryNeighborList == null || countryNeighborList.size() == 0) {
			return null;
		}

		return countryNeighborList.get(0);


	}


	/**
	 * 
	 * @param countryNameX
	 * @param coutryNameY
	 * @return
	 */
	public boolean validCountryNebrs(String countryNameX,String coutryNameY) {
		boolean isNebr = false;
		List<CountryNeighborsInfo> countryNbrList;
		query =  pm.newQuery(CountryNeighborsInfo.class,"countryName == paraCountryName");
		// declare params used above
		query.declareParameters("String paraCountryName");
		try {
			countryNbrList = (List<CountryNeighborsInfo>) query.execute(countryNameX);	
		} finally  {
			query.closeAll();	
		}
		if (countryNbrList == null || countryNbrList.size() == 0)
			return false;
		else
		{
			List<CountryNeighborsInfo> tempList = new ArrayList<CountryNeighborsInfo>();
			tempList = countryNbrList;

			for(CountryNeighborsInfo countryNbr : tempList) {
				List <String> strList = Utility.splitHashString(countryNbr.getNeighborCountry_name());				
				if(strList.contains(coutryNameY)) {
					isNebr = true;
					break;
				}

			}
		}
		return isNebr;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List<CountryHDI> getCountryListbyHDI(Criteria criteria) {
		List<CountryHDI> countryHDIList;
		Query query = pm.newQuery(CountryHDI.class);

		if(criteria.getLevel().equalsIgnoreCase("TOP")) {
			query.setOrdering("value descending");			
		}
		else if (criteria.getLevel().equalsIgnoreCase("AVERAGE")) {
		}
		else if (criteria.getLevel().equalsIgnoreCase("BOTTOM")) {
			query.setOrdering("value ascending");
		}
		try {
			query.setRange(0, criteria.getNumber());
			countryHDIList = (List<CountryHDI>) query.execute();	
		} finally  {
			query.closeAll();	
		}
		if (countryHDIList == null || countryHDIList.size() == 0)
			return null;
		else
			return countryHDIList;

	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List<CountryArea> getCountryListbyCountryArea(Criteria criteria) {

		List<CountryArea> countryAreaList;
		Query query = pm.newQuery(CountryArea.class);

		if(criteria.getLevel().equalsIgnoreCase("TOP")) {
			query.setOrdering("value descending");			
		}
		else if (criteria.getLevel().equalsIgnoreCase("AVERAGE")) {
		}
		else if (criteria.getLevel().equalsIgnoreCase("BOTTOM")) {
			query.setOrdering("value ascending");
		}
		try {
			countryAreaList = (List<CountryArea>) query.execute();	
		} finally  {
			query.closeAll();	
		}
		if (countryAreaList == null || countryAreaList.size() == 0)
			return null;
		else
			return countryAreaList;

	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List<CountryPopulation> getCountryListbyPopulation(Criteria criteria) {
		List<CountryPopulation> countryPopulationList;
		Query query = pm.newQuery(CountryPopulation.class);

		if(criteria.getLevel().equalsIgnoreCase("TOP")) {
			query.setOrdering("value descending");			
		}
		else if (criteria.getLevel().equalsIgnoreCase("AVERAGE")) {
		}
		else if (criteria.getLevel().equalsIgnoreCase("BOTTOM")) {
			query.setOrdering("value ascending");
		}
		try {
			query.setRange(0, criteria.getNumber());
			countryPopulationList = (List<CountryPopulation>) query.execute();	
		} finally  {
			query.closeAll();	
		}
		if (countryPopulationList == null || countryPopulationList.size() == 0)
			return null;
		else
			return countryPopulationList;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List<CountryGDP> getCountryListbyGDP(Criteria criteria) {
		List<CountryGDP> countryGdpList;
		Query query = pm.newQuery(CountryGDP.class);

		if(criteria.getLevel().equalsIgnoreCase("TOP")) {
			query.setOrdering("value descending");			
		}
		else if (criteria.getLevel().equalsIgnoreCase("AVERAGE")) {
		}
		else if (criteria.getLevel().equalsIgnoreCase("BOTTOM")) {
			query.setOrdering("value ascending");
		}
		try {
			query.setRange(0, criteria.getNumber());
			countryGdpList = (List<CountryGDP>) query.execute();	
		} finally  {
			query.closeAll();	
		}
		if (countryGdpList == null || countryGdpList.size() == 0)
			return null;
		else
			return countryGdpList;
	}


	/**
	 * 
	 * @param iso3code
	 * @return
	 */
	public List<String> getNearestCapitalCities (String iso3code) {

		List<String>capitalList = new ArrayList<String>();
		List<String> countryTobeProcessed = new ArrayList<String>();
		CountryNeighborsInfo countryNbrList;
		countryNbrList = getNbrCountries(iso3code);
		List <String> strList = Utility.splitHashString(countryNbrList.getNeighborCountry_iso3());
		for(String str:strList) {
			CountryCapitalMap countryCapitalMap = getcapitalAndMapbyISO3(str);
			if(countryCapitalMap == null) {}
			else {
				capitalList.add(countryCapitalMap.getCapitalName());
			}
			countryTobeProcessed.add(countryCapitalMap.getIso3());
		}
		for(String str:countryTobeProcessed) {
			try {
				System.out.println("String Country :"+str);
				countryNbrList = getNbrCountries(str);
				if(countryNbrList == null) { 

				}
				else {
					strList = Utility.splitHashString(countryNbrList.getNeighborCountry_iso3());
					for(String str1:strList) {
						if(countryTobeProcessed.contains(str1)) {
							continue;
						}
						CountryCapitalMap countryCapitalMap = getcapitalAndMapbyISO3(str1);
						if(countryCapitalMap == null) {}
						else {
							capitalList.add(countryCapitalMap.getCapitalName());
						}
					}
				}
			}catch (Exception e) {

			}
		}

		return capitalList;
	}


	
	public List<Double> getCountryListbyGDP() {
		List<Double>gdpList = new ArrayList<Double>();
		query =  pm.newQuery(CountryGDP.class);
		List<CountryGDP> countrygdpList;
		try {
			countrygdpList = (List<CountryGDP>) query.execute();

		} finally {
			query.closeAll();
		}
		
		for(CountryGDP countryGDP :countrygdpList){
			gdpList.add(countryGDP.getValue());
		}
		return gdpList;
		
	}
	
	public List<CountryPopulation> getCountryListbyPopulation() {
			
		query =  pm.newQuery(CountryPopulation.class);
		List<CountryPopulation> countrypopulationList;
		try {
			countrypopulationList = (List<CountryPopulation>) query.execute();

		} finally {
			query.closeAll();
		}
		
		return countrypopulationList;
		
	}
	
	public List<Double> getCountryListbyHDI() {
		
		List<Double>hdiList = new ArrayList<Double>();
		
		query =  pm.newQuery(CountryHDI.class);
		List<CountryHDI> countryhdiList;
		try {
			countryhdiList = (List<CountryHDI>) query.execute();

		} finally {
			query.closeAll();
		}
		
		for(CountryHDI countryPopulation :countryhdiList){
			hdiList.add(countryPopulation.getValue());
		}
		
		
		return hdiList;
		
	}
	
	
	public List<Double> getCountryListbyCountryArea() {
		
		List<Double>areaList = new ArrayList<Double>();
		
		return areaList;
		
	}
	
	
	
	
	
}
