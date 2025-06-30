package com.crm.realestatecrm.entity;

import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="logs")
public class Logs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(name="action")
	private String action;
	
	@Column(name="email")
	private String email;
	
	@Column(name="details")
	private String detials;
	
	public Logs() {
		
	}

	public Logs(String action, String email, String detials) {
		super();
		this.action = action;
		this.email = email;
		this.detials = detials;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetials() {
		return detials;
	}

	public void setDetials(String detials) {
		this.detials = detials;
	}


	@Override
	public String toString() {
		return "Logs [id=" + id + ", action=" + action + ", email=" + email + ", detials=" + detials + ", timesTamp="
				+"]";
	}
	
	
}
