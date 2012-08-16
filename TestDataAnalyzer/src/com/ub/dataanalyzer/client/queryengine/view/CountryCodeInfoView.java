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
import com.ub.dataanalyzer.shared.CountryCodeInfo;

public class CountryCodeInfoView extends ContentView implements ClickHandler{

	private ListBox countryNamesList;
	private Button submitButton;
	private Button cancelButton;
	private VerticalPanel vPanel;
	private DataBroker dataBroker;
	private DialogBox dialogBox;
	private Button closeButton;


	CountryCodeInfoView() {	
	}

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
		flexTable.setWidget(2, 0,submitButton);
		flexTable.setWidget(2, 1, cancelButton);
		submitButton.addClickHandler(this);
		cancelButton.addClickHandler(this);

		vPanel.add(flexTable);
		dataBroker = DataBroker.getInstance();
		dataBroker.getAllcountryNames(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for(String str : result) {
					countryNamesList.addItem(str);
				}			
			}

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Exception is fired");
				caught.printStackTrace();
			}
		});

		return vPanel;	
	}

	@Override
	public void removeView() {


	}

	@Override
	public void updateView() {


	}

	@Override
	public void onClick(ClickEvent event) {

		Widget sender = (Widget) event.getSource();
		if(sender == submitButton) {
			String countryName = countryNamesList.getItemText(countryNamesList.getSelectedIndex());
			submitButton.setEnabled(false);
			showCodeInfo(countryName);

		}		
		else if (sender == closeButton) {
			dialogBox.hide();
			submitButton.setEnabled(true);
			submitButton.setFocus(true);
		}
	}

	/**
	 * 
	 * @param countryName
	 */
	private void showCodeInfo(String countryName) {

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
		dialogVPanel.add(new HTML("<br><b>Country Code Information:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
		closeButton.addClickHandler(this);
		textToServerLabel.setText(countryName);
		serverResponseLabel.setText("");
		dataBroker.getAllcountryCodes(countryName, new AsyncCallback<CountryCodeInfo>() {
			
			@Override
			public void onSuccess(CountryCodeInfo result) {
				dialogBox.setText("Country Code Information");
				serverResponseLabel
						.removeStyleName("serverResponseLabelError");
				String info = "\nCountry UN code :" +result.getCountry_UN() + 
						"\n Country ISO3 :" + result.getIso3code()
						+"\n Country AGROVAOC Code :"+result.getCountry_AGROVOC();
				serverResponseLabel.setHTML(info);
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
