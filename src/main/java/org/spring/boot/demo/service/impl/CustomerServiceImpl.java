package org.spring.boot.demo.service.impl;

import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.repository.CustomerRepository;
import org.spring.boot.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("** Customer not found for id :: " + id));
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer update(int id, Customer customer) {
    	repository.findById(id).orElseThrow(() -> new NotFoundException("** Customer not found for id :: " + id));
    	customer.setId(id);
        return repository.save(customer);
    }

    @Override
    public void delete(int id) {
       repository.findById(id).ifPresent(Customer -> repository.delete(Customer));
    }
}
