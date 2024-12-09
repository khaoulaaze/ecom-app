package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;
    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bill.getCustomerId());
        System.out.println("customeeeeeer saved: " + customer);
        bill.getProductItems().forEach(productItem -> {
            Long productId = Long.parseLong(productItem.getProductId());
            Product product = productRestClient.findProductById(productId);
            System.out.println("prooooodddecut saved: " + product);
        });


        return bill;
    }
}