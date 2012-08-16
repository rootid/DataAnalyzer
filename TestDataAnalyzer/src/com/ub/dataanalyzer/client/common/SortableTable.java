package com.ub.dataanalyzer.client.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

@SuppressWarnings("deprecation")
public class SortableTable extends FlexTable implements Sortable,TableListener{

	// Holds the current column being sorted
	private int sortColIndex		=	-1;

	private int dontSortCol =99;
	// Holds the current direction of sort: Asc/ Desc
	private int sortDirection		=	-1;
	// The default image to show acending order arrow

	private String sortAscImage 	= "images/asc.png";

	//The default image to show descending order arrow
	private String sortDescImage	= "images/desc.png";

	// The default image to show the blank image
	// This is needed to paint the columns other than
	// the one which is being sorted.
	// Should be same length and width as the asc/ desc
	// images.
	private String blankImage		= "images/updown.png";

	// Holds the data rows of the table
	// This is a list of RowData Object
	@SuppressWarnings("unchecked")
	private List tableRows 	= new ArrayList();

	// Holds the data for the column headers
	@SuppressWarnings("unchecked")
	private List tableHeader = new ArrayList();

	private List<Boolean> isHeaderSortable = new ArrayList<Boolean>();

	/*
	 * Default Constructor
	 * 
	 * Calls the super class constructor
	 * and adds a TableListener object
	 */

	public SortableTable(){
		super();
		this.addTableListener(this);

	}

	/*
	 * addColumnHeader
	 * 
	 * Adds the Column Header to the table
	 * Uses the rowIndex 0 to add the header names.
	 * Renders the name and the asc/desc/blank gif 
	 * to the column
	 * 
	 * @param columnName (String)
	 * @param columnIndex (int)
	 */
	@SuppressWarnings("unchecked")
	public void addColumnHeader(String name, int index,Boolean isSoratble){
		tableHeader.add(index, name);
		isHeaderSortable.add(index, isSoratble);
		this.renderTableHeader(name, index,isHeaderSortable.get(index));
	}

	@SuppressWarnings("unchecked")
	public void addColumnHeader(String name, int index){
		tableHeader.add(index, name);
		isHeaderSortable.add(index, true);
		this.renderTableHeader(name, index,isHeaderSortable.get(index));
	}

	/*
	 * setValue
	 * 
	 * Sets the values in specifed row/column
	 * Expects a Comparable Object for sorting
	 *  
	 * @param rowIndex (int)
	 * @param columnIndex (int)
	 * @param Value (Comparable)
	 */
	@SuppressWarnings("unchecked")
	public void setValue(int rowIndex, int colIndex, Comparable value){
		// The rowIndex should begin with 1 as rowIndex 0 is for the Header
		// Any row with index == 0 will not be displayed.
		if(rowIndex == 0){
			return;
		}

		if((rowIndex-1) >= this.tableRows.size() || null == tableRows.get(rowIndex-1)){
			tableRows.add(rowIndex-1, new RowData());
		}

		RowData rowData = (RowData)this.tableRows.get(rowIndex-1); 
		rowData.addColumnValue(colIndex, value);
		this.setHTML(rowIndex, colIndex, "" + value.toString()+ "");
	}

	public int getDontSortCol() {
		return dontSortCol;
	}

	public void setDontSortCol(int dontSortCol) {
		this.dontSortCol = dontSortCol;
	}


	/*
	 * sort
	 * 
	 * Implementation of Sortable Interface, this
	 * method decribes how to sort the specified
	 * column. It checks the current sort direction
	 * and flips it
	 *  
	 * @param columnIndex (int)
	 */
	@SuppressWarnings("unchecked")
	public void sort(int columnIndex){
		Collections.sort(this.tableRows);
		if(this.sortColIndex != columnIndex){
			// New Column Header clicked
			// Reset the sortDirection to ASC
			this.sortDirection = SORT_ASC;
		}else{
			// Same Column Header clicked
			// Reverse the sortDirection
			this.sortDirection = (this.sortDirection == SORT_ASC)? SORT_DESC:SORT_ASC; 
		}
		this.sortColIndex = columnIndex;
	}

	/*
	 * onCellClicked
	 * 
	 * Implementation of Table Listener Interface, this
	 * method decribes what to do when a cell is clicked
	 * It checks for the header row and calls the sort 
	 * method to sort the table
	 *  
	 * @param sender (SourcesTableEvents)
	 * @param rowIndex (int)
	 * @param colIndex (int)
	 */
	public void onCellClicked(SourcesTableEvents sender, int row, int col) {
		if(row != 0 ){
			return;
		}
		if(col == dontSortCol){
			return;
		}
		this.setSortColIndex(col);
		this.sort(col);
		this.drawTable();
	}	
	
	/*
	 * getSortAscImage
	 * 
	 * Getter for Sort Ascending Image
	 * 
	 * @return String
	 */
	public String getSortAscImage() {
		return sortAscImage;
	}

	/*
	 * setSortAscImage
	 * 
	 * Setter for Sort Ascending Image
	 * 
	 * @param relative path + image name (String)
	 * e.g. images/asc.gif
	 */
	public void setSortAscImage(String sortAscImage) {
		this.sortAscImage = sortAscImage;
	}

	/*
	 * getSortDescImage
	 * 
	 * Getter for Sort Descending Image
	 * 
	 * @return String
	 */
	public String getSortDescImage() {
		return sortDescImage;
	}

	/*
	 * setSortDescImgage
	 * 
	 * Setter for Sort Descending Image
	 * 
	 * @param relative path + image name (String)
	 * e.g. images/desc.gif
	 */
	public void setSortDescImgage(String sortDescImgage) {
		this.sortDescImage = sortDescImgage;
	}

	/*
	 * getBlankImage
	 * 
	 * Getter for blank Image
	 * 
	 * @return String
	 */
	public String getBlankImage() {
		return blankImage;
	}

	/*
	 * setBlankImage
	 * 
	 * Setter for the blank Image
	 * 
	 * @param relative path + image name (String)
	 * e.g. images/blank.gif
	 */
	public void setBlankImage(String blankImage) {
		this.blankImage = blankImage;
	}

	/*
	 * drawTable
	 * 
	 * Renders the header as well as the body 
	 * of the table
	 */
	protected void drawTable(){
		this.displayTableHeader();
		this.displayTableBody();
	}

	/*
	 * displayTableHeader
	 * 
	 * Renders only the table header
	 */
	@SuppressWarnings("unchecked")
	private void displayTableHeader(){
		int colIndex=0;
		for(Iterator colHeaderIter = this.tableHeader.iterator(); colHeaderIter.hasNext();){
			String colHeader = (String)colHeaderIter.next();
			this.renderTableHeader(colHeader, colIndex,isHeaderSortable.get(colIndex));
			colIndex ++;
		}
	}

	/*
	 * displayTableBody
	 * 
	 * Renders the body or the remaining rows of the table
	 * except the header.
	 * It checks the sort direction and displays the rows 
	 * accordingly
	 */
	private void displayTableBody(){
		if(this.sortDirection == SORT_ASC || this.sortDirection == -1){
			// Ascending order and Default Display
			for(int rowIndex=0; rowIndex<tableRows.size(); rowIndex++){
				RowData columns = (RowData)tableRows.get(rowIndex);
				for(int colIndex=0; colIndex<columns.getColumnValues().size(); colIndex++){
					Object value = columns.getColumnValue(colIndex);
					if(null != value){
						this.setHTML(rowIndex+1, colIndex, value.toString());
					}
				}
			}
		}else{
			// Descending Order Display
			for(int rowIndex=tableRows.size()-1, rowNum = 1; rowIndex>=0; rowIndex--, rowNum++){
				RowData columns = (RowData)tableRows.get(rowIndex);
				for(int colIndex=0; colIndex<columns.getColumnValues().size(); colIndex++){
					Object value = columns.getColumnValue(colIndex);
					if(null != value){
						this.setHTML(rowNum, colIndex, value.toString());
					}
				}
			}
		}
	}

	/*
	 * setSortColIndex
	 * 
	 * Sets the current column index being sorted
	 * 
	 * @param column index being sorted (int)
	 */
	private void setSortColIndex(int sortIndex){
		for(int rowIndex=0; rowIndex<tableRows.size(); rowIndex++){
			RowData row = (RowData)tableRows.get(rowIndex);
			row.setSortColIndex(sortIndex);
		}
	}

	/*
	 * renderTableHeader
	 * Renders a particular column in the Table Header
	 * 
	 * @param Column Name (String)
	 * @param Column Index (int) 
	 */
	private void renderTableHeader(String name, int index , boolean isSortable){
		StringBuffer headerText = new StringBuffer();
		headerText.append("<b  class ='pointerCursor'>"+name+"</b>");
		if(isSortable)
		{
			headerText.append("&nbsp;<img border='0' src=");

			if(this.sortColIndex == index){
				if(this.sortDirection == SORT_ASC){
					headerText.append("'" + this.sortAscImage + "' alt='Ascending' ");	
				}else{
					headerText.append("'" + this.sortDescImage + "' alt='Descending' ");
				}
			}else{
				headerText.append("'" + this.blankImage + "'");
			}
			headerText.append("/>");
		}
		this.setHTML(0, index, headerText.toString());
	}

}
