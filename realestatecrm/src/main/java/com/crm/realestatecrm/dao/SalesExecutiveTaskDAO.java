package com.crm.realestatecrm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.SalesExecutiveTask;


public interface SalesExecutiveTaskDAO {
	void save(SalesExecutiveTask salesExecutiveTask);
	
	List<SalesExecutiveTask> getAllTask(String email, String userRole);
	
	void updateTask(SalesExecutiveTask salesExecutiveTask);
	
	SalesExecutiveTask getTaskByCustomerId(String email);
	
	int getSalesExecutiveTask(String email, String userRole);
	
	List<Integer> getTaskCount(String email, String userRole);
}
