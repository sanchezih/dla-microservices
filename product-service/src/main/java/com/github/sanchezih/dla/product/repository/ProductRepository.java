package com.github.sanchezih.dla.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sanchezih.dla.product.entity.Category;
import com.github.sanchezih.dla.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(Category category);
}