package com.learnSphere.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	private String name;
	private String email;
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;
	
	
	
	 public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Contact(int contactId, String name, String email, String message, Date creationTimestamp) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.message = message;
		this.creationTimestamp = creationTimestamp;
	}

    
	public int getContactId() {
		return contactId;
	}


	public void setContactId(int contactId) {
		this.contactId = contactId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getCreationTimestamp() {
		return creationTimestamp;
	}


	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
    

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name + ", email=" + email + ", message=" + message
				+ ", creationTimestamp=" + creationTimestamp + "]";
	}


	@PrePersist
     protected void onCreate() {
        creationTimestamp = new Date();
     }
	

}
