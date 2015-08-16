package com.spring.joon.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.joon.dao.CustomerDAO;
import com.spring.joon.model.Customer;
import com.spring.joon.servicedao.Service_Interface;

@Service("CustomerService")
@Transactional(readOnly = true)
public class CustomerService {
 
    // CustomerDAO is injected...
    private CustomerDAO customerDAO;
    
    @Transactional(readOnly = false)
    @Lazy
    public void addCustomer(Customer customer) {
        this.customerDAO.addCustomer(customer);
    }
    
    @Transactional
    @Lazy
    public void deleteCustomer(Customer customer) {
        this.customerDAO.deleteCustomer(customer);
    }
 
    @Transactional
    @Lazy
    public void updateCustomer(Customer customer) {
        this.customerDAO.updateCustomer(customer);
    }
    @Transactional
    @Lazy
    public Customer getCustomerById(int id) {
        return this.customerDAO.getCustomerById(id);
    }
 
    public List<Customer> getCustomers() {
        return this.customerDAO.getCustomers();
    }
 
    @Autowired
	@Qualifier("customerDAO")
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}