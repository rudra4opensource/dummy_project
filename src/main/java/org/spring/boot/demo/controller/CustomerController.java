package org.spring.boot.demo.controller;


import org.spring.boot.demo.annotation.LogObjectAfter;
import org.spring.boot.demo.annotation.LogObjectBefore;
import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @LogObjectAfter
    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        List<?> list = customerService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @LogObjectAfter
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok().body(customer);
    }


    @LogObjectBefore
    @LogObjectAfter
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.save(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		.path("/{id}")
        		.buildAndExpand(savedCustomer.getId())
        		.toUri();
        return ResponseEntity.created(uri).body(savedCustomer);
    }


    @LogObjectBefore
    @LogObjectAfter
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.update(id, customer);
        return ResponseEntity.ok().body(updatedCustomer);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        customerService.delete(id);
        return ResponseEntity.ok().body("Deleted successfully...!");
    }
}
