package com.ub.dataanalyzer.client;

import com.google.gwt.core.client.EntryPoint;
import com.ub.dataanalyzer.client.queryengine.view.AbstractScreen;
import com.ub.dataanalyzer.client.queryengine.view.ConcreteScreenFirst;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestDataAnalyzer implements EntryPoint {

	
	@Override
	public void onModuleLoad() {
	/*	 
	 * TODO: Implement following in UI
		1. Given a country name, return the UN country code and capital.
		2. Given lat/long as input tell me which country I am in. 
			Hint: There is a web service that returns max and min lat and 
			long for a given country code.
		3. Given 2 country names determine if they are bordering each other.
		4. Top ten list: Provide a list of countries with top 10 in
				a.	Population
				b.	GDP
				c.	Overall land area (size)
				d.	Human development index
		5. Bottom ten list: repeat the above for countries 
		at the lower end of these statistics.
		6. Given a country, display its map and list its capital.
		(You may have to get this information from some other source than FAO). About.com has maps of all countries that can be programmatically accessed. There is also: api.geonames.org.
		7. Given a city C and its coordinates {x,y}, 
		determine all capital cities accessible by land from 
		that city including the one in the same country as the city. 
		Next find the closest capital city from the city C excluding 
		the capital city of the country in which C is located.
		8.	Provide a feature to classify the countries into 
		N groups (3< N < 10) where N is user specified.  
		While various parameters can be used for classification,
		we will use only population.
		9. Export all the information discovered/mined 
		above as web services for programmatic consumption.
		10.	Write a WS-client application (app) 
		that consumes these web services and displays an 
		UI to allow users to query the application.
*/


		
		AbstractScreen concreteScreenFirst = new ConcreteScreenFirst();
	    // Add it to the root panel.
		concreteScreenFirst.fillStackPanel();
		concreteScreenFirst.fillContentPanel();
		//concreteScreenFirst.fillResultPanel();
	  
		
	}
	
}
