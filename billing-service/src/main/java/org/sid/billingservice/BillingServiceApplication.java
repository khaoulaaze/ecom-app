package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository,
										ProductItemRepository productItemRepository,
										CustomerRestClient customerRestClient,
										ProductRestClient productRestClient) {

		return args -> {
			// Récupérer tous les clients
			Collection<Customer> customers = customerRestClient.allCustomers().getContent();
			System.out.println("Customers retrieved:");
			customers.forEach(System.out::println);

			// Récupérer tous les produits
			Collection<Product> products = productRestClient.allProducts().getContent();
			System.out.println("Products retrieved:");
			products.forEach(System.out::println);

			// Pour chaque client, créer une facture et des items de produit
			customers.forEach(customer -> {
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customer.getId())
						.customer(customer)
						.productItems(new ArrayList<>())
						.build();
				Bill savedbill= billRepository.save(bill);
				System.out.println("Bill saved: " + bill);

				products.forEach(product -> {
					ProductItem productItem = ProductItem.builder()
							.bill(savedbill)
							.productId(String.valueOf(product.getId()))
							.product(product)
							.quantity(1 + new Random().nextInt(10))
							.unitPrice(product.getPrice())
							.discount(Math.random())
							.build();
					productItemRepository.save(productItem);
					System.out.println("ProductItem saved: " + productItem);

				});

			});

		};
	}
}
