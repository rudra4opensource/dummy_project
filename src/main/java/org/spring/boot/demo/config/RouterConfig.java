package org.spring.boot.demo.config;

import org.spring.boot.demo.handler.CustomerHandler;
//import org.springdoc.core.annotations.RouterOperation;
//import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//import com.spring.crud.demo.service.ReactiveCustomerService;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RouterConfig {

    private final CustomerHandler customerHandler = new CustomerHandler();

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return RouterFunctions.route()
                .GET("/route/customers", customerHandler::findAll)
                .GET("/route/customers/{id}", customerHandler::findById)
                .POST("/route/customers", customerHandler::save)
                .PUT("/route/customers/{id}", customerHandler::update)
                .DELETE("/route/customers/{id}", customerHandler::delete)
                .build();
    }


//    @Bean
//    public RouterFunction<ServerResponse> routerFunction() {
//
//        return RouterFunctions.route(RequestPredicates.GET("/route/customers), customerHandler::findAll)
//                .andRoute(RequestPredicates.GET("/route/customers/{id}"), customerHandler::findById)
//                .andRoute(RequestPredicates.POST("/route/customers"), customerHandler::save)
//                .andRoute(RequestPredicates.PUT("/route/customers/{id}"), customerHandler::update)
//                .andRoute(RequestPredicates.DELETE("/route/customers/{id}"), customerHandler::delete);
//    }

}
