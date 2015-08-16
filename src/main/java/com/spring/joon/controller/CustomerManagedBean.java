package com.spring.joon.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.spring.joon.model.Customer;
import com.spring.joon.service.CustomerService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="customerMB")
@RequestScoped
@SessionScoped
public class CustomerManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	private static final String FORM = "form";
	private static final String SHOW_TAG = "show_tag";
	/** This is most important part of project*/
	//Spring Customer Service is injected...
	@ManagedProperty(value="#{CustomerService}")
	private CustomerService customerService;

	List<Customer> customerList;
	private String userName;
	private String password_L;
	private String fullName;
	private String email;
	private String password_S;
	/**
	 * Add Customer
	 * @return String - Response Message like TO Success.xhtml
	 */
	public String addCustomer() {
		try {
			Customer customer = new Customer();
			customer.setUserName(getUserName());
			customer.setPassword(getPassword_L());
			this.customerService.addCustomer(customer);
			System.out.println("<<--------- " +" " + getUserName() + " " + getPassword_L() + "----->>");
			RequestContext context = RequestContext.getCurrentInstance();
	        FacesMessage message = null;
	        boolean loggedIn = false;
	        if(getUserName() != null && getUserName().equals("admin") && getPassword_L() != null && getPassword_L().equals("admin")) {
	            loggedIn = true;
	            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", userName);
	        } else {
	            loggedIn = false;
	            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
	        }
	        FacesContext.getCurrentInstance().addMessage(null, message);
	        context.addCallbackParam("loggedIn", loggedIn);
			return SHOW_TAG;
		} catch (DataAccessException e) {e.printStackTrace();}   
		return ERROR;
	}
	public String form(){
		System.out.println("fullname " + getFullName() + " Email id" + getEmail() +"password _S " + getPassword_S() );
		return SUCCESS;
	}
	/**
	 * Reset Fields
	 */
	/*public void reset() {
		this.setUserName("");
		this.setPassword("");
	}*/
	/**
	 * Get Customer List
	 * @return List - Customer List
	 */
	public List<Customer> getCustomerList() {
		customerList = new ArrayList<Customer>();
		customerList.addAll(this.customerService.getCustomers());
		return customerList;
	}
	/**
	 * Set Customer Service
	 * @param customerService ICustomerService - Customer Service
	 */
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/**
	 * Set Customer List
	 * @param customerList List - Customer List
	 */
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword_L() {
		return password_L;
	}

	public void setPassword_L(String password_L) {
		this.password_L = password_L;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword_S() {
		return password_S;
	}
	public void setPassword_S(String password_S) {
		this.password_S = password_S;
	}
	@Override
	public String toString() {
		return "CustomerManagedBean [userName=" + userName + ", password_L="
				+ password_L + ", fullName=" + fullName + ", email=" + email
				+ ", password_S=" + password_S + "]";
	}
	private String orientation = "horizontal";
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
}