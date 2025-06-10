package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.Properties;

public interface PropertiesDAO {
	void save(Properties properties);
	
	List<Properties> findAllProperties(String managerId);
	
	Properties findPropertyById(String id);
	
	void updateProperties(Properties properties);
}
