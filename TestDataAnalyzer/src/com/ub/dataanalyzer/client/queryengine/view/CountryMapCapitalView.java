package com.ub.dataanalyzer.client.queryengine.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.common.ClientModuleConstants;
import com.ub.dataanalyzer.client.services.DataBroker;
import com.ub.dataanalyzer.shared.CountryCapitalMap;

public class CountryMapCapitalView extends ContentView implements ClickHandler{


	private Button submitButton;
	private Button cancelButton;
	private VerticalPanel vPanel;
	private ListBox countryNamesList;
	private DataBroker dataBroker;
	private DialogBox dialogBox;
	private Button closeButton;
	
	@Override
	public Widget addView() {
		ClientModuleConstants clientConstant = GWT.create(ClientModuleConstants.class);
		FlexTable flexTable = new FlexTable();
		vPanel = new VerticalPanel();
		countryNamesList = new ListBox();
		countryNamesList.setVisibleItemCount(1);
		flexTable.setHTML(1, 0, clientConstant.CountryName());
		flexTable.setWidget(1, 1, countryNamesList);

		submitButton = new Button(clientConstant.Submit());
		cancelButton = new Button(clientConstant.Cancel());
		submitButton.addClickHandler(this);
		cancelButton.addClickHandler(this);
		flexTable.setWidget(2, 0,submitButton);
		flexTable.setWidget(2, 1, cancelButton);

		vPanel.add(flexTable);

		dataBroker = DataBroker.getInstance();
		
		dataBroker.getAllcountryNameCapitalMap(new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> result) {
				for(String str : result) {
					countryNamesList.addItem(str);
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return vPanel;
	}

	@Override
	public void removeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public VerticalPanel getvPanel() {
		return vPanel;
	}

	public void setvPanel(VerticalPanel vPanel) {
		this.vPanel = vPanel;
	}

	public ListBox getCountryNamesList() {
		return countryNamesList;
	}

	public void setCountryNamesList(ListBox countryNamesList) {
		this.countryNamesList = countryNamesList;
	}

	@Override
	public void onClick(ClickEvent event) {
		
		Widget sender = (Widget) event.getSource();
		if(sender == submitButton) {
			String countryName = countryNamesList.getItemText(countryNamesList.getSelectedIndex());
			submitButton.setEnabled(false);
			showCountryMapCapital(countryName);

		}		
		else if (sender == closeButton) {
			dialogBox.hide();
			submitButton.setEnabled(true);
			submitButton.setFocus(true);
		}

	}

	private void showCountryMapCapital(String countryName) {


		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Country Code Information");
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Country Name:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Country Capital and Map Information:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		closeButton.addClickHandler(this);
		textToServerLabel.setText(countryName);
		serverResponseLabel.setText("");
		dataBroker.getAllcountryCapitalMap(countryName, new AsyncCallback<CountryCapitalMap>() {
			
			@Override
			public void onSuccess(CountryCapitalMap result) {
				dialogBox.setText("Country Map and  Information");
				serverResponseLabel
						.removeStyleName("serverResponseLabelError");
				String info = "\nCountry UN code :" +result.getCapitalName() + 
						"\n Country continent name :" + result.getContinentName()
						+"\n Country Map url :"+result.getMapUrl();
				//googleLogo.setHTML("<img src=\"http://code.google.com/appengine/images/appengine-silver-120x30.gif\" alt=\"Powered by Google App Engine\" />");
				
				 String url = result.getMapUrl();
				serverResponseLabel.setHTML("<img src=\""+url+"\" />");
				dialogBox.center();
				closeButton.setFocus(true);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}



}
