package com.ub.dataanalyzer.client.queryengine.view;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.services.DataBroker;

public class CountryNbrValidationView extends ContentView implements 
ChangeHandler,ClickHandler {

	@UiTemplate("CountryNbrValidationView.ui.xml") 
	interface MyUiBinder extends UiBinder<Widget, CountryNbrValidationView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	@UiField ListBox countryXListBox;
	@UiField ListBox countryYListBox;
	@UiField Button submit;
	@UiField Button cancel;
	private DataBroker dataBroker;

	CountryNbrValidationView() {
		//initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public Widget addView() {
		Widget widget = uiBinder.createAndBindUi(this);
		dataBroker = DataBroker.getInstance();

		dataBroker.getAllcountryNameCapitalMap(new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for(String str : result) {
					countryXListBox.addItem(str);
					countryYListBox.addItem(str);
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
		countryXListBox.addChangeHandler(this);
		countryYListBox.addChangeHandler(this);
		submit.addClickHandler(this);
		cancel.addClickHandler(this);
		return widget;
	}

	@Override
	public void removeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}


	@Override
	public void onChange(ChangeEvent event) {
		Widget sender = (Widget) event.getSource();
		if(sender == countryXListBox) {
			System.out.println("Country X list box " +
					"clicked:"+countryXListBox.getItemText(countryXListBox.getSelectedIndex()));

		}
		else if (sender == countryXListBox) {
			System.out.println("Country Y list box clicked");
		}



	}


	@Override
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();

		String countryNamex = countryXListBox.getItemText(countryXListBox.getSelectedIndex());
		String countryNamey = countryXListBox.getItemText(countryYListBox.getSelectedIndex());
		if(sender == submit) {
			if(!(countryNamex.equalsIgnoreCase(countryNamey))) {
				dataBroker.isNeighboringcountry(countryNamex,
						countryNamey, new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						
						if(result == true) {
							Window.alert("Countries are neighbors" );
						}
						else {
							Window.alert("Countries are not neighbors" );
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						

					}
				});

			}
			else {
				System.out.println("Please provide different countries");
			}		
		}
		else if (sender == cancel) {

		}

	}
}
