package com.example.posbanco.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.example.posbanco.model.Product;
import com.example.posbanco.repository.ProductRepositoryOld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoryOld productRepository;

    /**
     * This method returns a list of products inserted in the "db"
     */
    public List<Product> getProducts(){
        //here goes the business rules
        return productRepository.getProducts();
    }

    /**
     * This method returns the product by it's id
     * @param id the product id to be find
     * @return the product if it's in the array
     */
    public Optional<Product> getProductById(Integer id){
        //here goes the business rules
        if( id.equals(null)){
            throw new InputMismatchException("ID not valid");
        }
        return  productRepository.getProductById(id);     
    }

    /**
     * This method adds a product to the "db" and gives itself a id 
     * @param product is the newest product added
     * @return the product itself
     */
    public Product addProduct(Product product){
        return productRepository.addProduct(product);
    }

    /**
         * This method remove a product filtered by it's id
         * @param id 
         */
    public void deleteProduct(Integer id){
        productRepository.deleteProduct(id);        
    }

    /**
     * This method updates the product in the list
     * @param product the new product
     * @return the product that was added
     */
    public Product updateProduct(Product product, Integer id){
        product.setId(id);
        return productRepository.updateProduct(product);
    }    
    
}
