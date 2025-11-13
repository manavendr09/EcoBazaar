package com.ecobazaar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecobazaar.model.Product;
import com.ecobazaar.repository.ProductRepository;

@SpringBootApplication
public class EcoBazaarApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoBazaarApplication.class, args);
    }

}
