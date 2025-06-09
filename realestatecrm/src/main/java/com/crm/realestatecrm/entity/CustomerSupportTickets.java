package com.crm.realestatecrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_support_tickets")
public class CustomerSupportTickets {
	
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private String ticketId;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="sales_exec_id")
	private String salesExectiveId;
	
	@Column(name="issue_category")
	private String issueCategory;
	
	@Column(name="description")
	private String discription;
	
	@Column(name="status")
	private String status;
	
	@Column(name="manager_id")
	private String managerId;
	
	public CustomerSupportTickets() {
		
	}

	public CustomerSupportTickets(String customerId, String salesExectiveId, String managerId, String issueCategory, String discription,
			String status) {
		super();
		this.customerId = customerId;
		this.salesExectiveId = salesExectiveId;
		this.issueCategory = issueCategory;
		this.discription = discription;
		this.status = status;
		this.managerId = managerId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSalesExectiveId() {
		return salesExectiveId;
	}

	public void setSalesExectiveId(String salesExectiveId) {
		this.salesExectiveId = salesExectiveId;
	}

	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
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
	
}
