package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.SalesExecutive;

public interface SalesExecutiveService {
	boolean save(SalesExecutive salesExecutive);
	
	List<SalesExecutive> getAllSalesExecutives(String email);
	
	SalesExecutive findSalesExecutiveByEmail(String email);
	
	int getSalesExecutiveCount(String managerId);
}
