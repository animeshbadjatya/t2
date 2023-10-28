package com.ecommerce.teamviewerecommerce.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name= "description")
    private String description;

    @Column(name= "unit_price")
    private BigDecimal unitPrice;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name= "active")
    private boolean active;

    @Column(name= "units_in_stock", nullable = false)
    private int unitsInStock;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL,orphanRemoval = true ) //{ CascadeType.PERSIST, CascadeType.MERGE } // Changed Mapping from Product to ProductID
    private Set<OrderItem> orderItems = new HashSet<>(); // this can be a list as well rather than HashSet, depends on implementation


    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

}
