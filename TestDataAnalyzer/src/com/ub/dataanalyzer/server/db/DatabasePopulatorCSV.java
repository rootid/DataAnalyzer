package com.ub.dataanalyzer.server.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.KeyFactory;
import com.ub.dataanalyzer.server.QueriesPerformer;
import com.ub.dataanalyzer.server.manager.PMF;
import com.ub.dataanalyzer.server.parser.CapitalParser;
import com.ub.dataanalyzer.server.parser.CountrynbrParser;
import com.ub.dataanalyzer.server.parser.Parser;
import com.ub.dataanalyzer.server.parser.RegionParser;
import com.ub.dataanalyzer.server.parser.XmlParser;
import com.ub.dataanalyzer.server.pojo.CountryCoOrdinate;
import com.ub.dataanalyzer.server.pojo.CountryRegionInfo;
import com.ub.dataanalyzer.server.tpservices.ThirdPartyCaller;
import com.ub.dataanalyzer.server.utility.KeyGenerator;
import com.ub.dataanalyzer.server.utility.Utility;
import com.ub.dataanalyzer.shared.CountryArea;
import com.ub.dataanalyzer.shared.CountryCapitalMap;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryNeighborInfo;
import com.ub.dataanalyzer.shared.CountryNeighborsInfo;
import com.ub.dataanalyzer.shared.CountryPopulation;

/**
 * This class populates database with csv.
 * @author vikram
 *
 */
public class DatabasePopulatorCSV {



	static DatabasePopulatorCSV dbPopulatorInstanceCSV = null;
	private PersistenceManager pm;
	private BufferedReader br;
	private String strLine = "";
	private StringTokenizer st = null;
	private int lineNumber = 0;
	private Query query;
	private List<CountryGeneralInfo> countryGenInfoList = null;
	private List<CountryCapitalMap> countryInfoList = null;
	private Map<String,String> countryMap;
	private Map<String,String> countryMap1;

	public static DatabasePopulatorCSV getDatabasePopulatorCSVInstance() {

		if(dbPopulatorInstanceCSV == null) {
			dbPopulatorInstanceCSV = new DatabasePopulatorCSV();
		}
		return dbPopulatorInstanceCSV;

	}

	private DatabasePopulatorCSV() {
		pm = PMF.get().getPersistenceManager();

	}

	public void closePMF() {

		pm.close();
	}
	
	public void clearDatabase() {
		pm.deletePersistentAll();
	}

	/**
	 * Populate first the base table
	 */
	public void populateBaseTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_gen_info.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{
					CountryGeneralInfo countryGenInfo = new CountryGeneralInfo();
					countryGenInfo.setCountryName(st.nextToken().replace("\"", ""));
					String iso3 = st.nextToken().replace("\"", "");
					countryGenInfo.setISO3(iso3);
					
					pm.makePersistent(countryGenInfo);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Get all Objects of CountryGeneralInfo
	 * @return
	 */
	public void getAllCountryGenInfoMap() {
		query =  pm.newQuery(CountryGeneralInfo.class);
		if (countryGenInfoList != null) {

		}
		else {
			countryGenInfoList = new ArrayList<CountryGeneralInfo>();
			countryMap = new HashMap<String, String>();
			try {
				countryGenInfoList = (List<CountryGeneralInfo>) query.execute();
				System.out.println("The size of the list ::"+countryGenInfoList.size());
				for (CountryGeneralInfo countryInfo : countryGenInfoList) {
					//System.out.println("Country names :"+countryInfo.getCountryName());
					countryMap.put(countryInfo.getISO3(),countryInfo.getCountryName());
				}

			} finally {
				query.closeAll();
			}
		}
	}

	
	/**
	 * Get all Objects of CountryGeneralInfo
	 * @return
	 */
	public void getAllCountryGenInfoMapFromCapital() {
		query =  pm.newQuery(CountryCapitalMap.class);
		if (countryInfoList != null) {

		}
		else {
			countryInfoList = new ArrayList<CountryCapitalMap>();
			countryMap1 = new HashMap<String, String>();
			try {
				countryInfoList = (List<CountryCapitalMap>) query.execute();
				System.out.println("The size of the list capital ::"+countryInfoList.size());
				for (CountryCapitalMap countryInfo : countryInfoList) {
					countryMap1.put(countryInfo.getIso3(),countryInfo.getCountryName());
					
				}

			} finally {
				query.closeAll();
			}
		}
	}
	
	/**
	 *  Populate the country_capital table
	 * 
	 */
	public void populateCountryCapitalMapTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_capital_map.csv";
			lineNumber = 0;
			br = new BufferedReader( new FileReader(path));
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{
					CountryCapitalMap countryCapitalMap = new CountryCapitalMap();
					countryCapitalMap.setCountryName(st.nextToken().replace("\"", ""));
					countryCapitalMap.setCapitalName(st.nextToken().replace("\"", ""));
					countryCapitalMap.setIso3(st.nextToken().replace("\"", ""));
					countryCapitalMap.setContinentName(st.nextToken().replace("\"", ""));
					countryCapitalMap.setMapUrl(st.nextToken().replace("\"", ""));		
					pm.makePersistent(countryCapitalMap);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/*
	 * Populates country_region_info table 0110
	 */
	public void populateCountryRegionTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_gen_info.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{	
					CountryRegionInfo countryRegionInfo = new CountryRegionInfo();
					countryRegionInfo.setIso3Code(st.nextToken().replace("\"", ""));
					countryRegionInfo.setRegionName(st.nextToken().replace("\"", ""));
					pm.makePersistent(countryRegionInfo);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Populate country_nbr table 1000
	 */
//	public void populateCountrynbrTable() {
//
//		try {
//			String path = Utility.getFilePath()+"\\db\\country_nbr_info.csv";
//			br = new BufferedReader(new FileReader(path));
//			lineNumber = 0;
//			getAllCountryGenInfoMap();
//			while( (strLine = br.readLine()) != null)
//			{
//				//break comma separated line using ","
//				st = new StringTokenizer(strLine, ";");
//				if(st.countTokens() == 0) {
//					System.out.println("Data unavailable");
//				}
//				while(st.hasMoreTokens())
//				{	
//					CountryNeighborInfo countryNbr = new CountryNeighborInfo();
//					countryNbr.setNeighborCountry_name(st.nextToken().replace("\"", ""));
//					countryNbr.setNeighborCountry_iso3(st.nextToken	().replace("\"", ""));
//					countryNbr.setIso3(st.nextToken().replace("\"", ""));
//					countryNbr.setCountryName(countryMap.get(countryNbr.getIso3()));
//					pm.makePersistent(countryNbr);
//					lineNumber++;
//					System.out.println("Total record "+lineNumber);
//
//				}
//
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//




	/**
	 * 
	 * @param iso3Code
	 */
	public void populateCountryCodeTable() {

		try {
			String path = Utility.getFilePath()+"\\db\\country_code_info.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{	
					CountryCodeInfo countryCodeInfo = new CountryCodeInfo();
					countryCodeInfo.setIso3code(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountryName(countryMap.get(countryCodeInfo.getIso3code()));
					countryCodeInfo.setCountry_UN(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_UNDP(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_FAOSTAT(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_FAOTERM(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_GAUL(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_AGROVOC(st.nextToken().replace("\"", ""));
					countryCodeInfo.setCountry_DBPEDIA_ID(st.nextToken().replace("\"", ""));
					pm.makePersistent(countryCodeInfo);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * Populates country_area_table
	 * 
	 */
	public void populateCountryAreaTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_area.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{	
					CountryArea countryArea = new CountryArea();
					countryArea.setCountryAreaType("COUNTRY_LAND_AREA");
					countryArea.setIso3Code(st.nextToken().replace("\"", ""));
					countryArea.setSize(Double.parseDouble(st.nextToken().replace("\"", "")));	
					countryArea.setUnit(st.nextToken().replace("\"", ""));
					countryArea.setYear(st.nextToken().replace("\"", ""));
					countryArea.setEstimation_type(st.nextToken().replace("\"", ""));	
					countryArea.setSourceInformation(st.nextToken().replace("\"", ""));
					countryArea.setCountryName(countryMap.get(countryArea.getIso3Code()));
					pm.makePersistent(countryArea);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param 
	 */

	public void populateCoOrdinateTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_co-ordinate.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{	
					CountryCoOrdinate countryCoOrdinate = new CountryCoOrdinate();
					countryCoOrdinate.setIso3Code(st.nextToken().replace("\"", ""));
					countryCoOrdinate.setCountryName(countryMap.get(countryCoOrdinate.getIso3Code()));
					countryCoOrdinate.setMaxLat(st.nextToken().replace("\"", ""));
					countryCoOrdinate.setMaxLong(st.nextToken().replace("\"", ""));
					countryCoOrdinate.setMinLat(st.nextToken().replace("\"", ""));
					countryCoOrdinate.setMinLong(st.nextToken().replace("\"", ""));
					pm.makePersistent(countryCoOrdinate);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param iso3Code
	 */
	public void populateGDPTable() {

		try {
			String path = Utility.getFilePath()+"\\db\\country_gdp.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{
					CountryGDP countryGDP = new CountryGDP();	
					countryGDP.setIso3Code(st.nextToken().replace("\"", ""));
					countryGDP.setCountryName(countryMap.get(countryGDP.getIso3Code()));
					countryGDP.setYear(st.nextToken().replace("\"", ""));
					countryGDP.setValue(Double.parseDouble(st.nextToken().replace("\"", "")));
					countryGDP.setSource(st.nextToken().replace("\"", ""));
					countryGDP.setEstimationType(st.nextToken().replace("\"", ""));
					countryGDP.setUnitType(st.nextToken().replace("\"", ""));
					pm.makePersistent(countryGDP);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 		
	}

	/**
	 * 
	 * 
	 */
	public void populateCountryPopulationTable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_population.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{
					CountryPopulation countryPopulation = new CountryPopulation();
					countryPopulation.setIso3Code(st.nextToken().replace("\"", ""));
					countryPopulation.setYear(st.nextToken().replace("\"", ""));
					countryPopulation.setValue(Double.parseDouble(st.nextToken().replace("\"", "")));
					countryPopulation.setUnit(st.nextToken().replace("\"", ""));
					countryPopulation.setSource(st.nextToken().replace("\"", ""));
					countryPopulation.setEstimationType(st.nextToken().replace("\"", ""));
					countryPopulation.setCountryName(countryMap.get(countryPopulation.getIso3Code()));
					pm.makePersistent(countryPopulation);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 		

	}		

	/**
	 * 
	 * @param 
	 */

	public void populateHDITable() {
		try {
			String path = Utility.getFilePath()+"\\db\\country_hdi.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{
					CountryHDI countryhdi = new CountryHDI();
					countryhdi.setIso3Code(st.nextToken().replace("\"", ""));
					countryhdi.setYear(st.nextToken().replace("\"", ""));
					countryhdi.setValue(Double.parseDouble(st.nextToken().replace("\"", "")));
					countryhdi.setSource(st.nextToken().replace("\"", ""));
					countryhdi.setEstimation_type(st.nextToken().replace("\"", ""));
					countryhdi.setCountryName(countryMap.get(countryhdi.getIso3Code()));
					pm.makePersistent(countryhdi);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 		

	}

	public void populateCountryNebrTable() {

		try {
			String path = Utility.getFilePath()+"\\db\\country_nebr_info.csv";
			br = new BufferedReader(new FileReader(path));
			lineNumber = 0;
			getAllCountryGenInfoMap();
			while( (strLine = br.readLine()) != null)
			{
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ";");
				if(st.countTokens() == 0) {
					System.out.println("Data unavailable");
				}
				while(st.hasMoreTokens())
				{	
					CountryNeighborsInfo countryNbr = new CountryNeighborsInfo();
					countryNbr.setIso3(st.nextToken().replace("\"", ""));
					countryNbr.setNeighborCountry_iso3(st.nextToken	().replace("\"", ""));
					countryNbr.setNeighborCountry_name(st.nextToken().replace("\"", ""));
					countryNbr.setCountryName(countryMap.get(countryNbr.getIso3()));
					pm.makePersistent(countryNbr);
					lineNumber++;
					System.out.println("Total record "+lineNumber);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}


}
