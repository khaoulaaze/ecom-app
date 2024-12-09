package org.sid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.sid.billingservice.model.Product;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bill bill;
    private int quantity;
    private double unitPrice;
    private double discount;
    @Transient
    private Product product;

    public double getAmount(){
        return unitPrice*quantity*(1-discount);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", bill='" + bill +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                ", product=" + product +
                '}';
    }
}

