package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.SalesExecutiveTask;

public interface SalesExecutiveTaskService {
	void save(SalesExecutiveTask salesExecutiveTask);
	
	List<SalesExecutiveTask> getAllTask(String email, String userRole);
	
	void updateTask(SalesExecutiveTask salesExecutiveTask);
	
	SalesExecutiveTask getTaskByCustomerId(String email);
	
	int getSalesExecutiveTask(String email, String userRole);
	
	List<Integer> getTaskCount(String email, String uerRole);
}
