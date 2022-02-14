package com.example.posbanco.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import com.example.posbanco.model.Product;
import com.example.posbanco.model.exception.ResourceNotFound;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryOld {
    private ArrayList<Product> products = new ArrayList<Product>();
    private Integer lastId = 0;

    /**
     * This method returns a list of products inserted in the "db"
     */
    public List<Product> getProducts(){
         return products;
    }

    /**
     * This method returns the product by it's id
     * @param id the product id to be find
     * @return the product if it's in the array
     */
    public Optional<Product> getProductById(Integer id){
        return  products
                    .stream()
                    .filter(product -> product.getId() == id)
                    .findFirst();        
    }

    /**
     * This method adds a product to the "db" and gives itself a id 
     * @param product is the newest product added
     * @return the product itself
     */
    public Product addProduct(Product product){
        lastId++;
        product.setId(lastId);
        products.add(product);
        return product;
    }

        /**
         * This method remove a product filtered by it's id
         * @param id 
         */
    public void deleteProduct(Integer id){
        products.removeIf(product -> product.getId() == id);
    }


    /**
     * This method updates the product in the list
     * @param product the new product
     * @return the product that was added
     */
    public Product updateProduct(Product product){
        Optional<Product> encontered = getProductById(product.getId());

        if(encontered.isEmpty()){
            throw new ResourceNotFound("Product not found");
        }
        
        deleteProduct(product.getId());
        products.add(product);
        return product;
    }   

    
}
