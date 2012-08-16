package com.ub.dataanalyzer.client.queryengine.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class ContentView extends Composite{

	public abstract Widget addView();
	public abstract void removeView();
	public abstract void updateView();
	
}
