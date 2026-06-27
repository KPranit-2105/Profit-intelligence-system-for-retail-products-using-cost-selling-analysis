package com.example.product_Spring_boot_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_Spring_boot_project.model.Product;

@Repository
public interface ProductRepo  extends JpaRepository<Product, Long>{

}
