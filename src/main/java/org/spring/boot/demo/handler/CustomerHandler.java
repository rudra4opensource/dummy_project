package org.spring.boot.demo.handler;


import lombok.AllArgsConstructor;

import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.service.ReactiveCustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerHandler {

    private final ReactiveCustomerService reactiveCustomerService = null;


    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        Flux<?> list = reactiveCustomerService.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(list, Customer.class);
    }


    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        Mono<Customer> customerMono = reactiveCustomerService.findById(id);
        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Customer> customer = serverRequest.bodyToMono(Customer.class);
        Mono<Customer> customerMono = reactiveCustomerService.save(customer.block());
        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        Mono<Customer> customer = serverRequest.bodyToMono(Customer.class);
        Mono<Customer> customerMono = reactiveCustomerService.update(id, customer.block());
        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.pathVariable("id"));
        Mono<Void> customerMono = reactiveCustomerService.delete(id);
        return ServerResponse.ok().body("Deleted successfully...!", String.class);
    }

}
