package com.ub.dataanalyzer.client.queryengine.view;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ub.dataanalyzer.client.common.Sortable;
import com.ub.dataanalyzer.client.common.SortableTable;

public class ResultView extends Composite{
	
	private VerticalPanel vPanel;
	
	
	public ResultView() {
		
	}
	
	public Widget addResultView() {
		vPanel = new VerticalPanel();
		final SortableTable sortableTable = new SortableTable();
		
		sortableTable.setWidth(500 + "px");
		sortableTable.setStyleName("sortableTable");
		sortableTable.setBorderWidth(1);
		sortableTable.setCellPadding(20);
		sortableTable.setCellSpacing(1);
		
		sortableTable.addColumnHeader("CountryName",0);
		sortableTable.addColumnHeader("CountryUNCode",1);
		sortableTable.addColumnHeader("CountryISO3",2);
		
		// The rowIndex should begin with 1 as rowIndex 0 is for the Header
		// Any row with index == 0 will not be displayed.
		sortableTable.setValue(1, 0, "India");
		sortableTable.setValue(1, 1, "IND");
		sortableTable.setValue(1, 2, "IND");
		
		vPanel.add(sortableTable);
		return vPanel;
	}

}
