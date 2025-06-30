package com.crm.realestatecrm.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.crm.realestatecrm.dao.AdminDAO;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;

@Service("adminService")
public class AdminServiceImple implements AdminService {

    private final AdminDAO adminDAO;

    public AdminServiceImple(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public List<Manager> getAllManagers() {
        return adminDAO.getAllManagers();
    }

    @Override
    public List<SalesExecutive> getAllSalesExecutives() {
        return adminDAO.getAllSalesExecutives();
    }

    @Override
    public List<Manager> searchManagersByEmail(String email) {
        return adminDAO.searchManagersByEmail(email);
    }

    @Override
    public List<SalesExecutive> searchSalesExecutivesByEmail(String email) {
        return adminDAO.searchSalesExecutivesByEmail(email);
    }

    @Override
    public boolean updateUserStatus(String email, int status) {
        return adminDAO.updateUserStatus(email, status);
    }
    
    @Override
    public int getUserStatus(String email) {
        return adminDAO.getUserStatus(email);
    }
    
    @Override
    public List<SalesExecutive> searchSalesExecutivesByManagerEmail(String managerEmail) {
        return adminDAO.searchSalesExecutivesByManagerEmail(managerEmail);
    }
}
