package com.spring.joon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	
	@Id
	@Column(name="USERNAME", nullable = false)
	private String userName;
	@Column(name="PASSWORD", nullable = false)
	private String password;
	/** If define unique constraint in ur model class u will never be enter same data in between all attribute */
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Customer [userName=" + userName + ", password=" + password
				+ "]";
	}
}