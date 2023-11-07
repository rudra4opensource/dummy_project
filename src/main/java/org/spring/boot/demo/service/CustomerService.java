package org.spring.boot.demo.service;

import java.util.List;

import org.spring.boot.demo.model.Customer;

public interface CustomerService {

    List<?> findAll();

    Customer findById(int id);

    Customer save(Customer customer);

    Customer update(int id, Customer customer);

    void delete(int id);
    
//	List<Customer> getAll();
//
//	List<Customer> getCustomerByFirstName(String firstName);

}
