package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentsession=sessionfactory.getCurrentSession();
		
		//create query
		Query<Customer> theQuery = currentsession.createQuery("from Customer order by lastName",Customer.class);
		
		//execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return results
		return customers;
		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		
		//get hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		//save or Update the customer ...
		currentSession.saveOrUpdate(theCustomer);
		
		
		
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		
		//now retrieve from database using primary key
		Customer theCustomer = currentSession.get(Customer.class,theId);
		return theCustomer;
		
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		//delete the object with primary key
		Query theQuery=currentSession.createQuery("delete from Customer where id=:theCustomerId");
		theQuery.setParameter("theCustomerId",theId);
		
		theQuery.executeUpdate();
	}

}
