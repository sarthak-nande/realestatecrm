package com.crm.realestatecrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.realestatecrm.dao.LogsDAO;
import com.crm.realestatecrm.entity.Logs;

@Service
public class LogsServiceImple implements LogsService{
	
	private LogsDAO logsDAO;
	
	public LogsServiceImple(LogsDAO logsDAO) {
		this.logsDAO = logsDAO;
	}

	@Override
	public void save(Logs logs) {
		logsDAO.save(logs);
		
	}

	@Override
	public List<Logs> getLogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
