package com.poly.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Category") // 👈 CHÍNH LÀ DÒNG QUAN TRỌNG
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
