package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.SalesExecutiveDAO;
import com.crm.realestatecrm.entity.SalesExecutive;

@Service
public class SalesExecutiveServiceImple implements SalesExecutiveService {
	
	private SalesExecutiveDAO salesExecutiveDAO;
	
	@Autowired
	public SalesExecutiveServiceImple(SalesExecutiveDAO salesExecutiveDAO) {
		this.salesExecutiveDAO = salesExecutiveDAO;
	}

	@Override
	public boolean save(SalesExecutive salesExecutive) {
		return salesExecutiveDAO.save(salesExecutive);
	}

	@Override
	public List<SalesExecutive> getAllSalesExecutives(String email) {
		return salesExecutiveDAO.getAllSalesExecutives(email);
	}

	@Override
	public SalesExecutive findSalesExecutiveByEmail(String email) {
		return salesExecutiveDAO.findSalesExecutiveByEmail(email);
	}

	@Override
	public int getSalesExecutiveCount(String managerId) {
		return salesExecutiveDAO.getSalesExecutiveCount(managerId);
	}
	
	

}
