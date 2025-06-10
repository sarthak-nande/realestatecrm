package com.crm.realestatecrm.dao;

import java.util.List;
import com.crm.realestatecrm.entity.Manager;
import com.crm.realestatecrm.entity.SalesExecutive;

public interface AdminDAO {
    List<Manager> getAllManagers();
    List<SalesExecutive> getAllSalesExecutives();
    List<Manager> searchManagersByEmail(String email);
    List<SalesExecutive> searchSalesExecutivesByEmail(String email);
    boolean updateUserStatus(String email, int status);
    int getUserStatus(String email);
    
    // New: Search Sales Executives by manager email
    List<SalesExecutive> searchSalesExecutivesByManagerEmail(String managerEmail);
}
