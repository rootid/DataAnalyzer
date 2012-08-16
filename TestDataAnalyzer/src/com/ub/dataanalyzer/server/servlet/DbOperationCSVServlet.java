package com.ub.dataanalyzer.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;
import com.ub.dataanalyzer.server.QueriesPerformer;
import com.ub.dataanalyzer.server.db.DatabasePopulatorCSV;
import com.ub.dataanalyzer.server.pojo.CountryRegionInfo;
import com.ub.dataanalyzer.server.utility.Utility;
import com.ub.dataanalyzer.shared.CountryGeneralInfo;
/**
 * This servlet is responsible to upload data on GAE bigTable.
 * @author vikram
 *
 */
public class DbOperationCSVServlet extends HttpServlet{
	


	private ServletConfig servletConfig;
	private String filePath;
	private DatabasePopulatorCSV dbPopulatorCSV;
	private QueriesPerformer queriesPerformer;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.servletConfig = config;		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		PersistenceManager pm;
		filePath = servletConfig.getServletContext().getRealPath("/");
		Utility.setFilePath(filePath);
		dbPopulatorCSV = DatabasePopulatorCSV.getDatabasePopulatorCSVInstance();
		queriesPerformer  = new QueriesPerformer();

		RemoteApiOptions options = new RemoteApiOptions()
		.server(ServerConstant.APPLICATION_NAME, ServerConstant.PORT_NUMBER)
		.credentials(ServerConstant.USER_NAME, ServerConstant.PASS_WORD);
		RemoteApiInstaller installer = new RemoteApiInstaller();
		try {
			installer.install(options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dbPopulatorCSV.clearDatabase(); //To clear all entries in database
			dbPopulatorCSV.populateBaseTable();
			dbPopulatorCSV.populateCountryCapitalMapTable();
			dbPopulatorCSV.populateCountryAreaTable();
			dbPopulatorCSV.populateCountryCodeTable();
			dbPopulatorCSV.populateCoOrdinateTable();
			dbPopulatorCSV.populateGDPTable();
			dbPopulatorCSV.populateHDITable();
			dbPopulatorCSV.populateCountryPopulationTable();
			dbPopulatorCSV.populateCountryNebrTable();
			dbPopulatorCSV.populateCountryNebrTable();
			dbPopulatorCSV.getAllCountryGenInfoMap();
			dbPopulatorCSV.closePMF();

		} finally {
			installer.uninstall();
		}
	

	}

	private void getAllCountryGenInfo() {
		List<CountryGeneralInfo> genInfo = queriesPerformer.getAllCountryGenInfo();
				for(CountryGeneralInfo countryGenInfo : genInfo) {
					System.out.println("All country inf :" + countryGenInfo.getCountryName() +"" +
							"iso3 :::" + countryGenInfo.getISO3());
				}

	}
	private void getAllRegions() {
		List<CountryRegionInfo> countryRegionInfoList = queriesPerformer.getAllRegionInfo();
		for(CountryRegionInfo countryRegionInfo : countryRegionInfoList) {
			System.out.println("All country inf :" + countryRegionInfo.getRegionName() +"" +
					"iso3 :::" + countryRegionInfo.getIso3Code());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Do Get executed");
		PrintWriter out = resp.getWriter();
		java.util.Date today = new java.util.Date();   
		out.println("<html>" +	
				"<body>" +
				"<h1 align=center>Test servlet</h1>"  +
				"<br>" + today + "</body>" + "</html>");
		out.close();	

	}




}
