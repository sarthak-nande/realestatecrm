package com.crm.realestatecrm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PropertiesDAOImple implements PropertiesDAO{
	
	private EntityManager entityManager;
	
	@Autowired
	public PropertiesDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(Properties properties) {
		if (properties.getPropertyId() == null || properties.getPropertyId().isEmpty()) {
	        
	        entityManager.persist(properties);
	    } else {
	        
	        entityManager.merge(properties);
	    }
	}

	@Override
	public List<Properties> findAllProperties(String managerId) {
		TypedQuery<Properties> query = entityManager.createQuery("select p from Properties p WHERE p.managerId = :managerId" , Properties.class);
		query.setParameter("managerId", managerId);
		List<Properties> properties = query.getResultList();
		return properties;
	}

	@Override
	public Properties findPropertyById(String id) {
	    TypedQuery<Properties> query = entityManager.createQuery(
	        "SELECT p FROM Properties p WHERE p.propertyId = :id", Properties.class);
	    query.setParameter("id", id);
	    
	    List<Properties> results = query.getResultList();
	    return results.isEmpty() ? null : results.get(0); // Avoid exception
	}

	@Override
	@Transactional
	public void updateProperties(Properties properties) {
		entityManager.merge(properties);
	}


}
