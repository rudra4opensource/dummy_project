package org.spring.boot.demo;

import lombok.extern.slf4j.Slf4j;

import org.spring.boot.demo.model.Customer;
import org.spring.boot.demo.repository.CustomerRepository;
import org.spring.boot.demo.utils.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class CustomerCRUDApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerCRUDApplication.class, args);
	}


	@Autowired
	private CustomerRepository customerRepository;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			List<Customer> customers = customerRepository.findAll();
			if (customers.isEmpty()) {
				log.info("******* Inserting Customers to DB *******");
				customerRepository.saveAll(HelperUtil.customersSupplier.get());
			} else {
				log.info("******* Customers stored in DB Size :: {}", customers.size());
				log.info("******* Customers stored in DB :: {}", customers);
			}
		};
	}

}
