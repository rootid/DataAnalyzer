package com.ub.dataanalyzer.client.queryengine.view;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
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
import com.ub.dataanalyzer.client.data.ClusteringCriteria;
import com.ub.dataanalyzer.client.services.DataBroker;
import com.ub.dataanalyzer.shared.CountryPopulation;

public class KclusteringView extends ContentView implements ClickHandler{

	@UiTemplate("KclusteringView.ui.xml") 
	interface MyUiBinder extends UiBinder<Widget, KclusteringView> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	@UiField ListBox paraListBox;
	@UiField ListBox noListBox;
	@UiField Button submit;
	@UiField Button cancel;
	private ClusteringCriteria clusteringCriteria;
	private DataBroker dataBorker;
	private int noCluster = 4;
	KclusteringView() {
		
	}

	@Override
	public Widget addView() {
		Widget widget = uiBinder.createAndBindUi(this);
		submit.addClickHandler(this);
		cancel.addClickHandler(this);
		dataBorker = DataBroker.getInstance();
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
			submit.setEnabled(false);
			clusteringCriteria = new ClusteringCriteria();
			clusteringCriteria.setNoCriteria(
					Integer.parseInt(noListBox.getItemText(noListBox.getSelectedIndex())));
			clusteringCriteria.setType(paraListBox.getItemText(paraListBox.getSelectedIndex()));
			//noCluster = Integer.parseInt(noListBox.getItemText(noListBox.getSelectedIndex()));
			showClusteringData();
		}		
		else if (sender == cancel) {
			submit.setEnabled(true);
			submit.setFocus(true);
		}

	}

	
	private void showClusteringData() {
	
		dataBorker.getCountryListbyPopulation(new AsyncCallback<List<CountryPopulation>>() {
			
			@Override
			public void onSuccess(List<CountryPopulation> result) {
				
				//nocluster
				List<PointCollection> clstrLst	= new ArrayList<KclusteringView.PointCollection>();
				clstrLst	= kMeans(result,noCluster);
				StringBuffer strbuffer = new StringBuffer();
				for(PointCollection clstr : clstrLst) {
					for(CountryPopulation pop : clstr.lPoints) {
						strbuffer.append("\n\nCluster No : " + clstr.Centroid.clstrId + "\n"+ pop.getCountryName() + "\t" + pop.getValue() + "\n");
					}
				}
				Window.alert(strbuffer.toString());
			}
			

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	class PopulationCluster {
		public int clstrId;
		public CountryPopulation population;
		
		public PopulationCluster() {
			clstrId		= 0;
			population 	= new CountryPopulation();
		}
	}
	
	private class PointCollection
	{
		public List<CountryPopulation> 	lPoints;
	    public PopulationCluster 		Centroid;
	    
	    public PointCollection() {
	    	lPoints		= new ArrayList<CountryPopulation>();
	    	Centroid	= new PopulationCluster();
	    }
	}
	
	private double FindDistance(PopulationCluster pt1, CountryPopulation pt2)
	{
	    double x1 = pt1.population.getValue();
	    double x2 = pt2.getValue();
	
	    //find euclidean distance
	    double distance = Math.abs(x2 - x1);
	    return (distance);
	}
	
	private int FindNearestCluster(List<PointCollection> allClusters, CountryPopulation point) {
		int nearestClusterNo	= 0;
		double nearestDistance	= Double.MAX_VALUE;
		double tempDistance		= 0;
		
		for(PointCollection cluster : allClusters) {
			tempDistance	= FindDistance(cluster.Centroid, point);
			if(tempDistance < nearestDistance) {
				nearestDistance	= tempDistance;
				nearestClusterNo= cluster.Centroid.clstrId;
			}
		}
		
		return nearestClusterNo;
	}
	
	private double FindCentroid(List<CountryPopulation> group) {
		double centroid	= 0;
		
		for(CountryPopulation pop : group) {
			centroid	+= pop.getValue();
		}
		centroid	/= group.size();
		
		return centroid;
	}
	
	private List<PointCollection> kMeans(List<CountryPopulation> points, int clusterCount)
	{
	    //divide points into equal clusters
	    List<PointCollection> allClusters 		= new ArrayList<PointCollection>();
	    
	    
//	    List<List<CountryPopulation>> allGroups = Lists.partition(points, points.size() / clusterCount);
	    List<List<CountryPopulation>> allGroups = new ArrayList<List<CountryPopulation>>();
	    int partitionSize	= points.size() / clusterCount;
	    for(int i=0 ; i<clusterCount ; i++) {
	    	allGroups.add(new ArrayList<CountryPopulation>());
	    	for(int j=0 ; j<partitionSize ; j++) {
	    		allGroups.get(i).add(points.get(i*partitionSize+j));
	    	}
	    }
	    
	    int clusterNo	= 0;
	    for (List<CountryPopulation> group : allGroups)
	    {
	        PointCollection cluster = new PointCollection();	        
	        cluster.Centroid.clstrId			= clusterNo++;
	        cluster.Centroid.population.setValue(FindCentroid(group));
	        cluster.lPoints.addAll(group);
	        
	        allClusters.add(cluster);
	    }
	 
	    //start k-means clustering
	    int movements = 1;
	    while (movements > 0)
	    {
	        movements = 0;
	 
	        for (PointCollection cluster : allClusters) //for all clusters
	        {
	            for (int pointIndex = 0; pointIndex < cluster.lPoints.size(); pointIndex++) //for all points in each cluster
	            {
	            	CountryPopulation point = cluster.lPoints.get(pointIndex);
	
	                int nearestCluster = FindNearestCluster(allClusters, point);
	                if (nearestCluster != cluster.Centroid.clstrId) //if point has moved
	                {
	                    if (cluster.lPoints.size() > 1) //each cluster shall have minimum one point
	                    {
	                    	boolean removedPoint = cluster.lPoints.remove(point);
	                    	if(removedPoint) {
	                    		allClusters.get(nearestCluster).lPoints.add(point);
		                        movements += 1;
	                    	}
	                    }
	                }
	            }
	        }
	    }
	 
	    return (allClusters);
	}

}
