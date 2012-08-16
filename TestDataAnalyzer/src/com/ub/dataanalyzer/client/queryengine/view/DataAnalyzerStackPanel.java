package com.ub.dataanalyzer.client.queryengine.view;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Widget;

public class DataAnalyzerStackPanel extends DecoratedStackPanel{
	
	//This list contains list of all the listener of respective type 
	//interface
	List<CustomEventHandler> eventListenerList;
	
	public DataAnalyzerStackPanel() {
		this.sinkEvents(Event.ONCLICK);
		eventListenerList = new ArrayList<CustomEventHandler>();
	}
	
	
	@Override
	public void add(Widget w, String stackText) {
		super.add(w, stackText);
	}
	
	
	@Override
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);
		for (CustomEventHandler handler : eventListenerList) {
			handler.onBrowse(event);
		}
	}
	
	@Override
	public void showStack(int index) {
		// TODO Auto-generated method stub
		super.showStack(index);
	}
	
	@Override
	public void setWidth(String width) {
		// TODO Auto-generated method stub
		super.setWidth(width);
	}


	public void addStackPanelEventHandler(CustomEventHandler 
			customEventHandler) {

		eventListenerList.add(customEventHandler);
		System.out.println("In the stack panel");	
	}
	
	

}
