package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.PropertiesDAO;
import com.crm.realestatecrm.entity.Properties;

@Service
public class PropertiesServiceImple implements PropertiesService{
	
	private PropertiesDAO propertiesDAO;
	
	@Autowired
	public PropertiesServiceImple(PropertiesDAO propertiesDAO) {
		this.propertiesDAO = propertiesDAO;
	}

	@Override
	public void save(Properties properties) {
		propertiesDAO.save(properties);
	}

	@Override
	public List<Properties> findAllProperties(String managerId) {
		return propertiesDAO.findAllProperties(managerId);
	}

	@Override
	public Properties findPropertyById(String id) {
		return propertiesDAO.findPropertyById(id);
	}

}
