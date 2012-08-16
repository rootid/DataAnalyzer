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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.data.Criteria;
import com.ub.dataanalyzer.client.queryengine.view.WhereIAmView.MyUiBinder;
import com.ub.dataanalyzer.client.services.DataBroker;
import com.ub.dataanalyzer.shared.CountryCodeInfo;
import com.ub.dataanalyzer.shared.CountryGDP;
import com.ub.dataanalyzer.shared.CountryHDI;
import com.ub.dataanalyzer.shared.CountryPopulation;
/**
 * 
 * @author vikram
 * This class is used to create Main screen for application.
 */

public class CountryCharInfoView extends ContentView  implements
ClickHandler, ChangeHandler{

	@UiTemplate("CountryCharInfoView.ui.xml") 
	interface MyUiBinder extends UiBinder<Widget, CountryCharInfoView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	@UiField ListBox paraListBox;
	@UiField ListBox levelListBox;
	@UiField ListBox noListBox;
	@UiField Button submit;
	@UiField Button cancel;
	private Criteria criteria;
	private DataBroker dataBroker;
	private DialogBox dialogBox;
	private Button closeButton;

	CountryCharInfoView() {
		//initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget addView() {
		Widget widget = uiBinder.createAndBindUi(this);
		paraListBox.addChangeHandler(this);
		levelListBox.addChangeHandler(this);
		noListBox.addChangeHandler(this);
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
	public void onClick(ClickEvent event) {

		Widget sender = (Widget) event.getSource();
		if(sender == submit) {
			criteria  = new Criteria();		
			criteria.setLevel(levelListBox.
					getItemText(levelListBox.getSelectedIndex()));
			criteria.setNumber(Integer.
					parseInt(noListBox.getItemText(noListBox.getSelectedIndex())));
			criteria.setParameter(paraListBox.
					getItemText(paraListBox.getSelectedIndex()));
			submit.setEnabled(false);
			showCountryCharInfo();

		}		
		else if (sender == closeButton) {
			dialogBox.hide();
			submit.setEnabled(true);
			submit.setFocus(true);
		}
		
		
	}
	

	private void showCountryCharInfo() {
		
		dataBroker = DataBroker.getInstance();
		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Country  Information");
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
		textToServerLabel.setText(criteria.getParameter());
		serverResponseLabel.setText("");
		
		
		if(criteria.getParameter().equalsIgnoreCase("GDP")) {
			
			dataBroker.getAllCountryGDPInfo(criteria, new AsyncCallback<List<CountryGDP>>() {
				
				@Override
				public void onSuccess(List<CountryGDP> result) {
					dialogBox.setText("Country GDP Information");
					serverResponseLabel
							.removeStyleName("serverResponseLabelError");
					StringBuffer strBuffer = new StringBuffer();
					for (CountryGDP countryGDP : result) {
						strBuffer.append("CountryName :"+countryGDP.getCountryName() + 
								"GDP :"+countryGDP.getValue());
					}
					Window.alert(strBuffer.toString());
					//serverResponseLabel.setHTML(info);
					dialogBox.center();
					closeButton.setFocus(true);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		else if (criteria.getParameter().equals("HDI")) {
			dataBroker.getAllCountryHDIInfo(criteria, new AsyncCallback<List<CountryHDI>>() {
				
				@Override
				public void onSuccess(List<CountryHDI> result) {
					dialogBox.setText("Country GDP Information");
					serverResponseLabel
							.removeStyleName("serverResponseLabelError");
				
					StringBuffer strBuffer = new StringBuffer();
					for (CountryHDI countryHDI : result) {
						strBuffer.append("CountryName :"+countryHDI.getCountryName() + "HDI :"
					+ countryHDI.getValue());
					}
					Window.alert(strBuffer.toString());
					//serverResponseLabel.setHTML(info);
					dialogBox.center();
					closeButton.setFocus(true);
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
				
					
				}
			});
		}
		else if (criteria.getParameter().equalsIgnoreCase("POPULATION")) {
			dataBroker.getAllCountryPopulation(criteria, new AsyncCallback<List<CountryPopulation>>() {
				
				@Override
				public void onSuccess(List<CountryPopulation> result) {
					dialogBox.setText("Country GDP Information");
					serverResponseLabel
							.removeStyleName("serverResponseLabelError");
				
					StringBuffer strBuffer = new StringBuffer();
					for (CountryPopulation countryPopulation : result) {
						strBuffer.append("CountryName :"+countryPopulation.getCountryName() +
								"Population :" + countryPopulation.getValue());
					}
					Window.alert(strBuffer.toString());
					//serverResponseLabel.setHTML(info);
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

	@Override
	public void onChange(ChangeEvent event) {
		
		
	}

}
