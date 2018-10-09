package org.hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int contactId;
	private String contactName;
	private String phoneNumber;
	private String userName;

	
	public int getContactId() {
		return contactId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setContactId(int contactId) {
		this.contactId = contactId;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "Contact [fullName=" + contactName + ", phoneNumber=" + phoneNumber + "]";
	}

}


