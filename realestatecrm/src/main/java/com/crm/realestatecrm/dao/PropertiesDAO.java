package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.Properties;

public interface PropertiesDAO {
	void save(Properties properties);
	
	List<Properties> findAllProperties();
	
	Properties findPropertyById(String id);
}
