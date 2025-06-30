package com.crm.realestatecrm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Logs;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class LogsDAOImple implements LogsDAO{
	
	private EntityManager entityManager;
	
	public LogsDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Logs logs) {
		entityManager.persist(logs);
	}

	@Override
	public List<Logs> getLogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
