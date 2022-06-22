package com.example.graalspring.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class JpaApplicationTests {

    private final CustomerRepository customerRepository;

    @Autowired
    JpaApplicationTests(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    void contextLoads() {
        var size = this.customerRepository.findAll().size();
        Assert.isTrue(size > 0, () -> "there should be more than one result!");
    }

}