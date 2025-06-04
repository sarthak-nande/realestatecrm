package com.crm.realestatecrm.service;

import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.ManagerDAOImple;
import com.crm.realestatecrm.dao.MangerDAO;
import com.crm.realestatecrm.entity.Manager;

@Service
public class ManagerServiceImple implements ManagerService {
	
	private MangerDAO mangerDAO;
	
	public ManagerServiceImple(MangerDAO mangerDAO) {
		this.mangerDAO = mangerDAO;
	}

	@Override
	public void save(Manager manager) {
		mangerDAO.save(manager);
		
	}

}
