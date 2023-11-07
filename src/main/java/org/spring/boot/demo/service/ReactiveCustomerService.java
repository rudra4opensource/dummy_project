package org.spring.boot.demo.service;

import org.spring.boot.demo.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveCustomerService {

    Flux<?> findAll();

    Mono<Customer> findById(int id);

    Mono<Customer> save(Customer customer);

    Mono<Customer> update(int id, Customer customer);

    Mono<Void> delete(int id);
}
