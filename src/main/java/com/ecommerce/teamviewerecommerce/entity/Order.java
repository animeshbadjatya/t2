package com.ecommerce.teamviewerecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "orders")
public class Order {

    // Add ProductIDs

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Long id;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems;

    @Column(name="total_Quantity")
    public int totalQuantity;

    @Column(name= "total_price")
    private BigDecimal totalPrice;

    @Column(name="customer_Id")
    public Long customerId;

    @Column(name="billing_address")
    public String billingAddress;

    @Column(name="status")
    public String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
}
