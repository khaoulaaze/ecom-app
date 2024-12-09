package org.sid.billingservice.entities;

import org.sid.billingservice.model.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "fullBill",types = Bill.class)
public interface BillProjection {
    Long getId();
    Date getbillingDate();
    Long getCustomerId();
    Customer getCustomer();
    List<ProductItem> getProductItems();

}
