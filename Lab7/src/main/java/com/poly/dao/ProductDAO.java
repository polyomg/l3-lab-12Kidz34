package com.poly.dao;

import java.util.List;

import com.poly.dto.InventoryReport;
import com.poly.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    @Query("SELECT new com.poly.dto.InventoryReport(o.category.name, SUM(o.price), COUNT(o)) "
            + "FROM Product o "
            + "GROUP BY o.category.name "
            + "ORDER BY SUM(o.price) DESC")
    List<InventoryReport> getInventoryByCategory();

    // Dùng DSL thay vì @Query
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

}
