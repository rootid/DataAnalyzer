package com.ub.dataanalyzer.client.queryengine.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.services.DataBroker;

/**
 * 
 * @author vikram
 *  This class shows the recent search results to users.
 */ 
public class RecentResultview extends ContentView implements ClickHandler{

	@UiTemplate("RecentResultview.ui.xml") 
	interface MyUiBinder extends UiBinder<Widget, RecentResultview> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	@UiField TextBox latTextBox;
	@UiField TextBox longTextBox;
	@UiField Button submit;
	@UiField Button cancel;
	private String httpResponse;
	private boolean isResponseAvailable;
	private GeoCodeParser geocodeParser;
	private CountryISO3Parser countryIso3Parser;
	private String strformat;
	private static String geoUrl = "http://ws.geonames.org/findNearbyPlaceName?lat=%s&lng=%s";
	private StringBuffer strUrl ;
	private StringBuffer strUrl2;
	private DataBroker dataBroker;
	private boolean flag =false;
	RecentResultview() {

	}

	@Override
	public Widget addView() {
		Widget widget = uiBinder.createAndBindUi(this);
		submit.addClickHandler(this);
		cancel.addClickHandler(this);
		strUrl = new StringBuffer();
		dataBroker = DataBroker.getInstance();
		return widget;
	}

	@Override
	public void removeView() {


	}

	@Override
	public void updateView() {


	}

	@UiHandler("submit")
	void handleSubmitClick(ClickEvent e) {


	}

	@UiHandler("cancel")
	void handleCancelClick(ClickEvent e) {

		Window.alert("Cancel , UiBinder");
		latTextBox.setValue("");
		longTextBox.setValue("");
	}

	@Override
	public void onClick(ClickEvent event) {
		Widget sender = (Widget) event.getSource();
		if(sender == submit) {
			//submit.setEnabled(false);	
			strUrl.append("http://ws.geonames.org/findNearbyPlaceName?lat=");
			strUrl.append(latTextBox.getText());
			strUrl.append("&lng=");
			strUrl.append(longTextBox.getText());
			System.out.println("The request "+strUrl.toString());
			callThirdPartyService(strUrl.toString());

		}		
		else if (sender == cancel) {

			//submit.setEnabled(true);
			submit.setFocus(true);
		}

	}


	private void callThirdPartyService(String url) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);


		try {
			Request request = builder.sendRequest(null,new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						geocodeParser = new GeoCodeParser(response.getText());

						String iso2 = geocodeParser.getCountryIso2();
						strUrl2 = new StringBuffer();
						strUrl2.append("http://api.geonames.org/countryInfo?country=");
						strUrl2.append(iso2);
						strUrl2.append("&username=demo&style=full");
						System.out.println("url iso3:"+strUrl2.toString());
						callThirdPartyService2(strUrl2.toString());
						//System.out.println("The respnse text :"+geocodeParser.getCountryName());
					}

				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub

				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void callThirdPartyService2(String url) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);


		try {
			Request request = builder.sendRequest(null,new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						countryIso3Parser = new CountryISO3Parser(response.getText());
						String iso3Code = countryIso3Parser.getCountryIso3();
						flag = true;
						if(flag == true) {
							dataBroker.getAllNearestCapitalList(iso3Code, new AsyncCallback<List<String>>() {

								@Override
								public void onSuccess(List<String> result) {
									StringBuffer strbuf = new StringBuffer();
									for(String str : result) {
										strbuf.append("Nearest capital :"+str+"\n");
									}
									Window.alert(strbuf.toString());
									flag = false;
								}

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}
							});
						}
						//System.out.println("The respnse text :"+geocodeParser.getCountryName());
					}

				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub

				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 



	}


}
