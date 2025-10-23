package com.poly.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
