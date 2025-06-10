package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.SalesExecutiveTaskDAO;
import com.crm.realestatecrm.entity.SalesExecutiveTask;

@Service
public class SalesExecutiveTaskServiceImple implements SalesExecutiveTaskService{
	
	private SalesExecutiveTaskDAO salesExecutiveTaskDAO;
	
	@Autowired
	public SalesExecutiveTaskServiceImple(SalesExecutiveTaskDAO salesExecutiveTaskDAO) {
		this.salesExecutiveTaskDAO = salesExecutiveTaskDAO;
	}

	@Override
	public void save(SalesExecutiveTask salesExecutiveTask) {
		salesExecutiveTaskDAO.save(salesExecutiveTask);
	}

	@Override
	public List<SalesExecutiveTask> getAllTask(String email, String userRole) {
		return salesExecutiveTaskDAO.getAllTask(email, userRole);
	}

	@Override
	public void updateTask(SalesExecutiveTask salesExecutiveTask) {
		salesExecutiveTaskDAO.updateTask(salesExecutiveTask);
	}

	@Override
	public SalesExecutiveTask getTaskByCustomerId(String email) {
		return salesExecutiveTaskDAO.getTaskByCustomerId(email);
	}

	@Override
	public List<Integer> getTaskCount(String email, String uerRole) {
		return salesExecutiveTaskDAO.getTaskCount(email, uerRole);
	}

	@Override
	public int getSalesExecutiveTask(String email, String userRole) {
		return salesExecutiveTaskDAO.getSalesExecutiveTask(email, userRole);
	}

}
