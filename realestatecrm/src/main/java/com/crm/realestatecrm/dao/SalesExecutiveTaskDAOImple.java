package com.crm.realestatecrm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.crm.realestatecrm.entity.Customer;
import com.crm.realestatecrm.entity.SalesExecutive;
import com.crm.realestatecrm.entity.SalesExecutiveTask;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class SalesExecutiveTaskDAOImple implements SalesExecutiveTaskDAO {
	
	private EntityManager entityManager;
	
	public SalesExecutiveTaskDAOImple(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void save(SalesExecutiveTask salesExecutiveTask) {
		entityManager.persist(salesExecutiveTask);
	}

	@Override
	public List<SalesExecutiveTask> getAllTask(String email, String userRole) {
		List<SalesExecutiveTask> tasks = new ArrayList<>();
		
		if(userRole.equals("ROLE_SALES")) {
			System.out.println("Hi");
			TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("select s from SalesExecutiveTask s where s.salesExecutive = :email",SalesExecutiveTask.class);
			query.setParameter("email", email);
			tasks = query.getResultList();
			
		}
		
		if(userRole.endsWith("ROLE_MANAGER")) {
			TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("select s from SalesExecutiveTask s where s.managerId = :email",SalesExecutiveTask.class);
			query.setParameter("email", email);
			tasks = query.getResultList();
			
		}
		
		return tasks;
	}

	@Override
	@Transactional
	public void updateTask(SalesExecutiveTask updatedSalesExecutive) {
		System.out.println(updatedSalesExecutive.getTaskId());
		TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("SELECT c FROM SalesExecutiveTask c WHERE c.taskId = :taskId", SalesExecutiveTask.class);
		    query.setParameter("taskId", updatedSalesExecutive.getTaskId());

		    SalesExecutiveTask results = query.getSingleResult(); // Avoid exception

		    SalesExecutiveTask existingCustomer = results;

		    if (existingCustomer == null) {
		        throw new RuntimeException("Customer not found for ID: " + updatedSalesExecutive.getCustomer());
		    }

		    if (updatedSalesExecutive.getStatus() != null) {
		        existingCustomer.setStatus(updatedSalesExecutive.getStatus());
		    }
		    
		    if (updatedSalesExecutive.getTaskType() != null) {
		        existingCustomer.setTaskType(updatedSalesExecutive.getTaskType());
		    }
		    
		    if (updatedSalesExecutive.getDescription() != null) {
		        existingCustomer.setDescription(updatedSalesExecutive.getDescription());
		    }
		    
		    if (updatedSalesExecutive.getTaskDate() != null) {
		        existingCustomer.setTaskDate(updatedSalesExecutive.getTaskDate());
		    }
		    
		    if (updatedSalesExecutive.getSalesExecutive() != null) {
		        existingCustomer.setSalesExecutive(updatedSalesExecutive.getSalesExecutive());
		    }

		    entityManager.merge(existingCustomer); // Save changes
	}

	@Override
	public SalesExecutiveTask getTaskByCustomerId(String email) {
		SalesExecutiveTask salesExecutiveTask = new SalesExecutiveTask();
		TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("select s from SalesExecutiveTask s where s.taskId = :taskId",SalesExecutiveTask.class);
		query.setParameter("taskId", email);
		salesExecutiveTask = query.getSingleResult();
		return salesExecutiveTask;
	}

	@Override
	public int getSalesExecutiveTask(String email, String userRole) {
		List<SalesExecutiveTask> tasks = new ArrayList<>();
		int count = 0;
		if(userRole.equals("ROLE_SALES")) {
			TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("select s from SalesExecutiveTask s where s.salesExecutive = :email AND s.status = :status",SalesExecutiveTask.class);
			query.setParameter("email", email);
			query.setParameter("status", "Pending");
			tasks = query.getResultList();
			count = tasks.size();
		}
		
		if(userRole.endsWith("ROLE_MANAGER")) {
			TypedQuery<SalesExecutiveTask> query = entityManager.createQuery("select s from SalesExecutiveTask s where s.managerId = :email AND s.status = :status",SalesExecutiveTask.class);
			query.setParameter("email", email);
			query.setParameter("status", "Pending");
			tasks = query.getResultList();
			count = tasks.size();
		}
		
		return count;
	}
	
	@Override
	public List<Integer> getTaskCount(String email, String userRole){
		List<SalesExecutiveTask> pendingTasks = new ArrayList<>();
		List<SalesExecutiveTask> completedTasks = new ArrayList<>();
		List<SalesExecutiveTask> cancelledTasks = new ArrayList<>();
		
		List<Integer> tasksCounts = new ArrayList<>();
		
		if(userRole.equals("ROLE_SALES")) {
			TypedQuery<SalesExecutiveTask> pending = entityManager.createQuery("select s from SalesExecutiveTask s where s.salesExecutive = :email AND s.status = :status",SalesExecutiveTask.class);
			pending.setParameter("email", email);
			pending.setParameter("status", "Pending");
			pendingTasks = pending.getResultList();
			
			TypedQuery<SalesExecutiveTask> completed = entityManager.createQuery("select s from SalesExecutiveTask s where s.salesExecutive = :email AND s.status = :status",SalesExecutiveTask.class);
			completed.setParameter("email", email);
			completed.setParameter("status", "Completed");
			completedTasks = completed.getResultList();
			
			TypedQuery<SalesExecutiveTask> cancelled = entityManager.createQuery("select s from SalesExecutiveTask s where s.salesExecutive = :email AND s.status = :status",SalesExecutiveTask.class);
			cancelled.setParameter("email", email);
			cancelled.setParameter("status", "Cancelled");
			cancelledTasks = cancelled.getResultList();
			
			tasksCounts.add(pendingTasks.size());
			tasksCounts.add(completedTasks.size());
			tasksCounts.add(cancelledTasks.size());
			
		}
		
		if(userRole.endsWith("ROLE_MANAGER")) {
			TypedQuery<SalesExecutiveTask> pending = entityManager.createQuery("select s from SalesExecutiveTask s where s.managerId = :email AND s.status = :status",SalesExecutiveTask.class);
			pending.setParameter("email", email);
			pending.setParameter("status", "Pending");
			pendingTasks = pending.getResultList();
			
			TypedQuery<SalesExecutiveTask> completed = entityManager.createQuery("select s from SalesExecutiveTask s where s.managerId = :email AND s.status = :status",SalesExecutiveTask.class);
			completed.setParameter("email", email);
			completed.setParameter("status", "Completed");
			completedTasks = completed.getResultList();
			TypedQuery<SalesExecutiveTask> cancelled = entityManager.createQuery("select s from SalesExecutiveTask s where s.managerId = :email AND s.status = :status",SalesExecutiveTask.class);
			cancelled.setParameter("email", email);
			cancelled.setParameter("status", "Cancelled");
			cancelledTasks = cancelled.getResultList();
			
			tasksCounts.add(pendingTasks.size());
			tasksCounts.add(completedTasks.size());
			tasksCounts.add(cancelledTasks.size());
		}
		
		return tasksCounts;
	}
	
	
	

}
