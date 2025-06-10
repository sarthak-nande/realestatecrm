package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.Properties;


public interface PropertiesService {
	void save(Properties properties);
	
	List<Properties> findAllProperties(String managerId);
	
	Properties findPropertyById(String id);
}
