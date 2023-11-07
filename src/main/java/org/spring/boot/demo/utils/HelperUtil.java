package org.spring.boot.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.spring.boot.demo.model.Customer;


public class HelperUtil {

    private HelperUtil() {
    }


    public static Supplier<List<Customer>> customersSupplier = () ->
            Arrays.asList(
                    Customer.builder().firstName("Apple").lastName("Phone").dateOfBirth("12/12/2020").isActive(false).build(),
                    Customer.builder().firstName("Mango").lastName("Fruit").dateOfBirth("11/11/2019").isActive(false).build(),
                    Customer.builder().firstName("Pumpkin").lastName("Size").dateOfBirth("04/11/2020").isActive(false).build(),
                    Customer.builder().firstName("Grape").lastName("Seed").dateOfBirth("09/10/2003").isActive(false).build(),
                    Customer.builder().firstName("Black").lastName("Color").dateOfBirth("07/08/2003").isActive(false).build(),
                    Customer.builder().firstName("Mike").lastName("Tyre").dateOfBirth("01/02/2022").isActive(false).build()

            );

}
