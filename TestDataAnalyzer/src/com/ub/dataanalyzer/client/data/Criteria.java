package com.ub.dataanalyzer.client.data;

import java.io.Serializable;

public class Criteria implements Serializable{

	public enum CriteriaType {
		AVERAGE,TOP,BOTTOM
	}
	private String parameter;
	private int number;
	private CriteriaType criteriaType;
	private String level;
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Criteria() {
	
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public CriteriaType getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(CriteriaType criteriaType) {
		this.criteriaType = criteriaType;
	}
	
	
}
