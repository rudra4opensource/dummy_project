package org.spring.boot.demo.controller;

import io.swagger.v3.oas.annotations.Operation;

import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.service.ReactiveCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/customers")
public class ReactiveCustomerController {

    @Autowired
    private ReactiveCustomerService reactiveCustomerService;

    @Operation(summary = "Try this endpoint in chrome, postman doesn't support for reactive programming")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<?>> findAll() {
        Flux<?> list = reactiveCustomerService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping("/{id}")
    public Mono<?> findById(@PathVariable int id) {
        return reactiveCustomerService.findById(id);
    }


    @PostMapping
    public Mono<?> save(@RequestBody Customer customer) {
        return reactiveCustomerService.save(customer);
    }


    @PutMapping("/{id}")
    public Mono<?> update(@PathVariable int id, @RequestBody Customer customer) {
        return reactiveCustomerService.update(id, customer);
    }


    @DeleteMapping("/{id}")
    public Mono<?> delete(@PathVariable int id) {
        Mono<Void> monoVoid = reactiveCustomerService.delete(id);
        return Mono.just("Deleted successfully...!");
    }
}
