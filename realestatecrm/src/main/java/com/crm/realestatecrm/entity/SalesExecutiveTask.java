package com.crm.realestatecrm.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_executive_tasks")
public class SalesExecutiveTask {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String taskId;

    @Column(name = "sales_exec_id")
    private String salesExecutive;

    
    @Column(name = "customer_id")
    private String customer;

    @Column(name = "task_date", nullable = false)
    private String taskDate;

    @Column(name = "task_type", nullable = false)
    private String taskType;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name="manager_id")
    private String managerId;
    
    public SalesExecutiveTask() {
    	
    }
    
	public SalesExecutiveTask(String salesExecutive, String customer, String taskDate, String taskType,
			String description, String status, String managerId) {
		super();
		this.salesExecutive = salesExecutive;
		this.customer = customer;
		this.taskDate = taskDate;
		this.taskType = taskType;
		this.description = description;
		this.status = status;
		this.managerId = managerId;
	}

	public String getSalesExecutive() {
		return salesExecutive;
	}

	public void setSalesExecutive(String salesExecutive) {
		this.salesExecutive = salesExecutive;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	

}

