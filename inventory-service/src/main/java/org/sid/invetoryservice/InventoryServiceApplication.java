package org.sid.invetoryservice;

import org.sid.invetoryservice.entities.Product;
import org.sid.invetoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Computer").quantity(12).price(900).build(),
                            Product.builder().name("Printer").quantity(120).price(1900).build(),
                            Product.builder().name("Smartphone").quantity(10).price(5500).build()
                    )
            );
        };
    }
}