package com.rx.service;

import com.github.javafaker.Faker;
import com.rx.model.Customer;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by roshane on 5/18/2017.
 */
public class CustomerService {

    private static final Faker faker =
            new Faker(Locale.getDefault(), new Random(System.currentTimeMillis()));

    public static List<Customer> getCustomer(int size) {
        return IntStream.range(0, size)
                .boxed()
                .map(i -> new Customer(faker.number().randomDigit(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress())
                ).collect(Collectors.toList());
    }

}
