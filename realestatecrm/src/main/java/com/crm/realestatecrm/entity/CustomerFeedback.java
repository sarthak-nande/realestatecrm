package com.crm.realestatecrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_feedback")
public class CustomerFeedback {
	
	@Id
    @Column(name = "feedback_id")
	@GeneratedValue(strategy = GenerationType.UUID)
    private String feedbackId;

    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "resolution_status")
    private String resolutionStatus;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comments")
    private String comments;
    
    @Column(name="customer_id")
    private String customerId;
    
    public CustomerFeedback() {
    	
    }

	public CustomerFeedback(String ticketId, String resolutionStatus, Integer rating, String comments,String customerId) {
		super();
		this.ticketId = ticketId;
		this.resolutionStatus = resolutionStatus;
		this.rating = rating;
		this.comments = comments;
		this.customerId = customerId;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getResolutionStatus() {
		return resolutionStatus;
	}

	public void setResolutionStatus(String resolutionStatus) {
		this.resolutionStatus = resolutionStatus;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
