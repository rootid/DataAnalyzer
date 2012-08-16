package com.ub.dataanalyzer.client.queryengine.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.common.ClientModuleConstants;
import com.ub.dataanalyzer.client.data.DataCollector;
import com.ub.dataanalyzer.client.services.DataBroker;

public class ConcreteScreenFirst extends AbstractScreen implements 
			CustomEventHandler{

	private DataAnalyzerStackPanel dataAnalyzerStackPanel;
	private ClientModuleConstants clientModuleConstants;
	private VerticalPanel dataAnalyzerPanel;
	private ContentView contentView;
	private ResultView resultview;
	private boolean isAlreadyhit;
	private DataCollector dataCollector;
	
	private List<String> countryNameList;
	
	public ConcreteScreenFirst() {
		super(true, true, true);
		dataAnalyzerStackPanel = new DataAnalyzerStackPanel();
		isAlreadyhit = false;
	}

	@Override
	protected void initStackPanel() {			
		dataAnalyzerStackPanel = new DataAnalyzerStackPanel();
		
	}

	@Override
	protected void initContentPanel() {
		
	}

	@Override
	protected void initResultPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillContentPanel() {
		contentView = new WhereIAmView();
		super.getContentPanelContainer().add(contentView.addView());

	}
	
	@Override
	public void fillResultPanel() {
		resultview = new ResultView();
		super.getResultPanelContainer().add(resultview.addResultView());
	}

	@Override
	public void fillStackPanel() {
		dataAnalyzerPanel = new VerticalPanel();
		dataAnalyzerStackPanel.addStackPanelEventHandler(this);
		clientModuleConstants = 
				GWT.create(ClientModuleConstants.class);

		VerticalPanel vPanel1 = new VerticalPanel();
		VerticalPanel vPanel2 = new VerticalPanel();
		VerticalPanel vPanel3 = new VerticalPanel();
		VerticalPanel vPanel4 = new VerticalPanel();
		VerticalPanel vPanel5 = new VerticalPanel();
		VerticalPanel vPanel6 = new VerticalPanel();
		VerticalPanel vPanel7 = new VerticalPanel();
		
		dataAnalyzerStackPanel.add(vPanel1,clientModuleConstants.WhereIam(),false);
		dataAnalyzerStackPanel.add(vPanel2,clientModuleConstants.Countrycode(),false);
		dataAnalyzerStackPanel.add(vPanel3,clientModuleConstants.Countrynbr(),false);
		dataAnalyzerStackPanel.add(vPanel4,clientModuleConstants.CountryCharinfo(),false);
		dataAnalyzerStackPanel.add(vPanel5,clientModuleConstants.ShowAllInfo(),false);
		dataAnalyzerStackPanel.add(vPanel6,clientModuleConstants.KClusteringSol(),false);
		dataAnalyzerStackPanel.add(vPanel7, "Nearest neighboring capital", false);		
		dataAnalyzerStackPanel.addStackPanelEventHandler(this);

		super.getStackPanelContainer().add(dataAnalyzerStackPanel);

	}
	
	@Override
	public void onBrowse(Event event) {
		
		
	
		int index = dataAnalyzerStackPanel.getSelectedIndex();
		index += 1;  // As index starts from 0 we added 1
		
		if(index >= 1) {
			//clear the container
			super.contentPanelContainer.clear(); 

			if(index == 1) {
				contentView = new WhereIAmView();
			}
			else if (index ==2) {
				contentView = new CountryCodeInfoView();
				//super.getResultPanelContainer().clear();
			}
			else if(index == 3) {
				contentView = new CountryNbrValidationView();
				//super.getResultPanelContainer().add(resultview.addResultView());
			}
			else if(index == 4) {
				contentView = new CountryCharInfoView();
				//super.getResultPanelContainer().clear();
			}
			else if (index == 5) {
				contentView = new CountryMapCapitalView();	
			}
			else if (index == 6){
				contentView = new KclusteringView();
			}
			else if (index == 7){
				contentView = new RecentResultview();
			}
			if((index == 2 || index == 3 || index == 5) & (!isAlreadyhit)) 
			{
				//To populate the countryList data only once in application lifetime
//				dataCollector =  DataCollector.getInstance();
//				dataCollector.populateCountryNameList();
//				dataCollector.getMapandCapital("India");
//				isAlreadyhit = true;
				
				dataCollector = DataCollector.getInstance();
//				dataCollector.getCountriesbyGDPCriteria();
//				dataCollector.getCountriesbyCriteria();
				
				
			}
			
			super.getContentPanelContainer().add(contentView.addView());
		}
	}
	
	

	public List<String> getCountryNameList() {
		return countryNameList;
	}

	@Override
	public void addComponents() {
		
	}

	@Override
	public void removeComponents() {
		
	}

	@Override
	public void clearContainer() {
		
	}

}
