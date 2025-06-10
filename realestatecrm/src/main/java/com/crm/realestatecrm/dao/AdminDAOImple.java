package com.crm.realestatecrm.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.entity.Users;

@Repository
public class AdminDAOImple implements AdminDAO {

    private final EntityManager entityManager;

    public AdminDAOImple(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Manager> getAllManagers() {
        TypedQuery<Manager> query = entityManager.createQuery("from Manager", Manager.class);
        return query.getResultList();
    }

    @Override
    public List<SalesExecutive> getAllSalesExecutives() {
        TypedQuery<SalesExecutive> query = entityManager.createQuery("from SalesExecutive", SalesExecutive.class);
        return query.getResultList();
    }

    @Override
    public List<Manager> searchManagersByEmail(String email) {
        TypedQuery<Manager> query = entityManager.createQuery(
                "from Manager where lower(email) like :search", Manager.class);
        query.setParameter("search", "%" + email.toLowerCase() + "%");
        return query.getResultList();
    }

    @Override
    public List<SalesExecutive> searchSalesExecutivesByEmail(String email) {
        TypedQuery<SalesExecutive> query = entityManager.createQuery(
                "from SalesExecutive where lower(email) like :search", SalesExecutive.class);
        query.setParameter("search", "%" + email.toLowerCase() + "%");
        return query.getResultList();
    }
    
    @Override
    public List<SalesExecutive> searchSalesExecutivesByManagerEmail(String managerEmail) {
        TypedQuery<SalesExecutive> query = entityManager.createQuery(
            "from SalesExecutive where lower(managerEmail) = :managerEmail", SalesExecutive.class);
        query.setParameter("managerEmail", managerEmail.toLowerCase());
        return query.getResultList();
    }

    @Override
    @Transactional
    public boolean updateUserStatus(String email, int status) {
        Users user = entityManager.find(Users.class, email);
        if (user == null) {
            return false;
        }
        user.setEnabled(status);
        entityManager.merge(user);
        
        // If the user being updated is a Manager, update all associated Sales Executives.
        List<Manager> managers = entityManager
                .createQuery("from Manager where email = :email", Manager.class)
                .setParameter("email", email)
                .getResultList();
        if (!managers.isEmpty()) {
            List<SalesExecutive> salesExecs = entityManager
                .createQuery("from SalesExecutive where lower(managerEmail) = :email", SalesExecutive.class)
                .setParameter("email", email.toLowerCase())
                .getResultList();
            for (SalesExecutive se : salesExecs) {
                Users seUser = entityManager.find(Users.class, se.getEmail());
                if (seUser != null) {
                    seUser.setEnabled(status);
                    entityManager.merge(seUser);
                }
            }
        }
        
        return true;
    }
    
    @Override
    public int getUserStatus(String email) {
        Users user = entityManager.find(Users.class, email);
        return user != null ? user.getEnabled() : 0;
    }
}
