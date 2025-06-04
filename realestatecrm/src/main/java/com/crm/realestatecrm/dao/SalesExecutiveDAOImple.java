package com.crm.realestatecrm.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Authorities;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.entity.Users;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class SalesExecutiveDAOImple implements SalesExecutiveDAO {
	
	private EntityManager entityManager;
	private BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	public SalesExecutiveDAOImple(EntityManager entityManager, BCryptPasswordEncoder byBCryptPasswordEncoder) {
		this.entityManager = entityManager;
		this.byBCryptPasswordEncoder = byBCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public boolean save(SalesExecutive salesExecutive) {
		Users user = new Users();
		Authorities authorities = new Authorities();
		
		String email = salesExecutive.getEmail();
		Users existingUser = entityManager.find(Users.class, email);
		System.out.println(existingUser);
		if (existingUser != null) {
		    return false; 
		}

		salesExecutive.setPassword(byBCryptPasswordEncoder.encode(salesExecutive.getPassword()));
		
		user.setEnabled(1);
		user.setUsername(salesExecutive.getEmail());
		user.setPassword(salesExecutive.getPassword());
		
		authorities.setUsername(salesExecutive.getEmail());
		authorities.setAuthority("ROLE_SALES");
		
		entityManager.persist(user);
		entityManager.persist(authorities);
		entityManager.persist(salesExecutive);
		
		return true;
	}

}
