package com.spring.joon.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.joon.model.Customer;
import com.spring.joon.servicedao.Service_Interface;

@Repository
public class CustomerDAO implements CustomerInterface{
    
    private SessionFactory sessionFactory;
 
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void addCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().beginTransaction();
        this.sessionFactory.getCurrentSession().save(customer);
        this.sessionFactory.getCurrentSession().close();
        /*Transaction trans=((SharedSessionContract) sessionFactory).beginTransaction();
        sessionFactory.save(customer);
        trans.commit();*/
    }
    
    public void deleteCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().delete(customer);
    }
    
    public void updateCustomer(Customer customer) {
        this.sessionFactory.getCurrentSession().update(customer);
    }
    /**
     * Get customer
     * @param  id int  
     * @return customer
     */
    public Customer getCustomerById(int id) {
        List list = this.sessionFactory.getCurrentSession()
                                            .createQuery("from com.mkyong.user.model.Customer  where id=?")
                                            .setParameter(0, id).list();
        return (Customer)list.get(0);
    }
    /**
     * Get customer List
     * @return List - customer list
     */
    public List<Customer> getCustomers() {
        List list = this.sessionFactory.getCurrentSession().createQuery("from com.mkyong.user.model.Customer").list();
        return list;
    }
}