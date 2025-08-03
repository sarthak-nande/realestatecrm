package com.crm.realestatecrm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Authorities;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ManagerDAOImple implements MangerDAO {
	
	private EntityManager entityManager;  // Handles database operations
	
	private final BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	@Autowired
	public ManagerDAOImple(EntityManager entityManager, BCryptPasswordEncoder byBCryptPasswordEncoder) {
		this.entityManager = entityManager;
		this.byBCryptPasswordEncoder = byBCryptPasswordEncoder;
	}

	@Override
	@Transactional  // Ensures all DB operations happen in one transaction
	public void save(Manager manager) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		manager.setPassword(byBCryptPasswordEncoder.encode(manager.getPassword()));
		
		user.setEnabled(1);
		user.setUsername(manager.getEmail());
		user.setPassword(manager.getPassword());
		
		authorities.setUsername(manager.getEmail());
		authorities.setAuthority("ROLE_MANAGER");
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(manager);
	}
}
