package com.example.posbanco.repository;

import com.example.posbanco.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    
}
