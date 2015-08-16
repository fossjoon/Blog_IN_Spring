package com.spring.joon.dao;

import java.util.List;

import com.spring.joon.model.Customer;

public interface CustomerInterface {
	public void addCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public List<Customer> getCustomers();

}
