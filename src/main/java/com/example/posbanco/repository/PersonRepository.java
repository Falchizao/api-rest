package com.example.posbanco.repository;

import com.example.posbanco.model.Person;
import com.example.posbanco.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    
}
