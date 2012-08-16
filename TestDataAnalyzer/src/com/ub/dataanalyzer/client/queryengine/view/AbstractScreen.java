package com.ub.dataanalyzer.client.queryengine.view;

import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.RootPanel;

public abstract class AbstractScreen {
	
	
	private boolean showResultPanel;
	private boolean showStackPanel;
	private boolean showContentPanel;
	protected RootPanel stackPanelContainer;
	protected RootPanel contentPanelContainer;
	protected RootPanel resultPanelContainer;
	
	public AbstractScreen(boolean showResultPanel,boolean showStackPanel,
			boolean showContentPanel) {
		this.showResultPanel = showResultPanel;
		this.showStackPanel = showStackPanel;
		this.showContentPanel = showContentPanel;
		this.stackPanelContainer = RootPanel.get("stackPanelContainer");
		this.contentPanelContainer = RootPanel.get("contentPanelContainer");
		//this.resultPanelContainer = RootPanel.get("resultPanelContainer");
		
		if(this.showStackPanel){
			initStackPanel();
		}
			
		if(this.showContentPanel) {
			initContentPanel();
		}
		
		if(this.showResultPanel) {
			initResultPanel();
		}
		
	}
	
	protected abstract void initStackPanel();
	protected abstract void initContentPanel();
	protected abstract void initResultPanel();
	public abstract void fillStackPanel();
	public abstract void fillContentPanel();
	public abstract void fillResultPanel();
	public abstract void onBrowse(Event event);
	public abstract void addComponents();
	public abstract void removeComponents();
	public abstract void clearContainer();
	

//	
//	protected void clearComponent(){
//		if(this.showStackPanel) {
//			stackPanelContainer.clear();
//		}
//		if(this.showContentPanel) {
//			contentPanelContainer.clear();
//		}
//		if(this.showResultPanel) {
//			tabPanelContainer.clear();
//		}
//	}

	public boolean isshowResultPanel() {
		return showResultPanel;
	}

	public boolean isShowStackPanel() {
		return showStackPanel;
	}

	public boolean isShowContentPanel() {
		return showContentPanel;
	}

	public boolean isShowResultPanel() {
		return showResultPanel;
	}

	public RootPanel getStackPanelContainer() {
		return stackPanelContainer;
	}

	public RootPanel getContentPanelContainer() {
		return contentPanelContainer;
	}


	public RootPanel getResultPanelContainer() {
		return resultPanelContainer;
	}
	

}
