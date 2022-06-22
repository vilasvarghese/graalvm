package com.example.graalspring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Stream;


@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}

@Component
record Initializr(CustomerRepository repository)
       implements ApplicationRunner {

   @Override
   public void run(ApplicationArguments args) throws Exception {
       Stream.of("A", "B", "C", "D")
               .map(c -> new Customer(null, c))
               .map(this.repository::save)
               .forEach(System.out::println);
   }
}

@RestController
record CustomerRestController(CustomerRepository repository) {

    @GetMapping("/customers")
    Collection<Customer> customers() {
        return this.repository.findAll();
    }
}

interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

