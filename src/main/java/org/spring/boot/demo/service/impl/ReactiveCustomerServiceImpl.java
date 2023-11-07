package org.spring.boot.demo.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.repository.CustomerRepository;
import org.spring.boot.demo.service.ReactiveCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class ReactiveCustomerServiceImpl implements ReactiveCustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Flux<?> findAll() {
        List<Customer> customers = repository.findAll();

        return Flux.fromIterable(customers)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(customer -> log.info("*** {}", customer))
                .map(customer -> customer)
                .log();     // log() to print event stream on console. Check console for event logs
    }

    @Override
    public Mono<Customer> findById(int id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("** Customer not found for id :: " + id));
        return Mono.just(customer)
                .log();     // log() to print event stream on console. Check console for event logs
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        customer = repository.save(customer);
        return Mono.just(customer)
                .log();     // log() to print event stream on console. Check console for event logs
    }

    @Override
    public Mono<Customer> update(int id, Customer customer) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("** Customer not found for id :: " + id));
        customer.setId(id);
        return this.save(customer);
    }

    @Override
    public Mono<Void> delete(int id) {
        repository.findById(id).ifPresent(repository::delete);
        return Mono.empty();
    }
}
