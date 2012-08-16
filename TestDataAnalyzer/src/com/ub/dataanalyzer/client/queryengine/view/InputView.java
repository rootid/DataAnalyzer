package com.ub.dataanalyzer.client.queryengine.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.common.ClientModuleConstants;

public class InputView {
	
	ClientModuleConstants clientModuleConstants;
	HorizontalPanel hPanel;
	
	public InputView() {
		clientModuleConstants = GWT.create(ClientModuleConstants.class);
	}
	
	public Widget addInputView() {
		
		String allyears = clientModuleConstants.AllYears();
		String [] allyearList = allyears.split(",");
		String allParameters = clientModuleConstants.AllParameters();
		String [] allParameterList = allParameters.split(",");
		
		FlexTable mainFlexTable = new FlexTable();
		mainFlexTable.setWidth("100%");
		mainFlexTable.setBorderWidth(0);
		
		FlexTable flexDataTable = new FlexTable();
		
		ListBox yearListBox = new ListBox(true);
		for (String year : allyearList) {
			yearListBox.addItem(year);
		}
		yearListBox.setVisibleItemCount(4);
		
		ListBox parameterListBox = new  ListBox();
		for (String parameter : allParameterList) {
			parameterListBox.addItem(parameter);
		}
		parameterListBox.setVisibleItemCount(1);
		
		
		Button analyzeButton = new Button();
		analyzeButton.setHTML(clientModuleConstants.Analyze());
		
		flexDataTable.setHTML(0, 0, clientModuleConstants.Parameter());
		flexDataTable.setWidget(0,2,parameterListBox);
		flexDataTable.setHTML(0, 4, clientModuleConstants.Year());
		flexDataTable.setWidget(0, 6, yearListBox);
		flexDataTable.setWidget(3, 6, analyzeButton);
		hPanel = new HorizontalPanel();
		hPanel.add(flexDataTable);
		return hPanel;
	}
	

}
