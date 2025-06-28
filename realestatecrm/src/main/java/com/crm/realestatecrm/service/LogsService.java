package com.crm.realestatecrm.service;

import java.util.List;

import com.crm.realestatecrm.entity.Logs;

public interface LogsService {
	void save(Logs logs);
	
	List<Logs> getLogs();
}
