package com.ub.dataanalyzer.server.manager;

import java.util.List;

public interface DAO {
	
	void addContact(Object object);
	void removeContact(Object object);
	void updateContact(Object object);
	List<Object> getObjects();

}
