package com.crm.realestatecrm.dao;

import java.util.List;

import com.crm.realestatecrm.entity.Logs;

public interface LogsDAO {
	void save(Logs logs);
	
	List<Logs> getLogs();
}
