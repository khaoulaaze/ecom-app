package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.sid.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductItem> productItems = new ArrayList<>();
    @Transient private Customer customer;

    public double getTotal(){
        double somme=0;
        for(ProductItem pi:productItems){
            somme+=pi.getAmount();
        }
        return somme;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billingDate=" + billingDate +
                ", customerId=" + customerId +
                ", productItems=" + productItems +
                ", customer=" + customer +
                '}';
    }
}