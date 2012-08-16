package com.ub.dataanalyzer.server.manager;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		//Get response
		
		//call parser
		//submit the response
		
        
		PersistenceManager pm = PMF.get().getPersistenceManager();

		CountryGenInfo countryGenInfo = new CountryGenInfo
				("India", "IND");
		CountryGenInfo countryGenInfo2 = new CountryGenInfo
				("Bharat", "HP");

		//Add the object in persistent store
		//
		
			pm.makePersistent(countryGenInfo);
			pm.makePersistent(countryGenInfo2);
	
		
		//Search the object
		  CountryGenInfo e = pm.getObjectById(CountryGenInfo.class,"India");
		  
		  System.out.println("The value "+e.getCountryName() +"ISO3 :" +e.getIso3_code());

		   
				pm.close();
			
	}
	

	
}
