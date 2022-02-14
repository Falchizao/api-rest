package com.example.posbanco.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.posbanco.model.Product;
import com.example.posbanco.repository.ProductRepository;
import com.example.posbanco.repository.ProductRepositoryOld;
import com.example.posbanco.shared.ProductDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method returns a list of products inserted in the "db"
     */
    public List<ProductDTO> getProducts(){ //Here we are trying to get a new type of product to use in requests and responses
        //here goes the business rules
        List<Product> products = productRepository.findAll();  //Now we get all the products into other list to cast it 
        return products.stream().map(product -> new ModelMapper().map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    /**
     * This method returns the product by it's id
     * @param id the product id to be find
     * @return the product if it's in the array
     */
    public Optional<ProductDTO> getProductById(Integer id){
        //here goes the business rules
        Optional<Product> product = productRepository.findById(id);    
        
        if(product.isEmpty()){
            throw new InputMismatchException("Product not found!");
        }

        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return  Optional.of(dto);
    }

    /**
     * This method adds a product to the "db" and gives itself a id 
     * @param product is the newest product added
     * @return the product itself
     */
    public ProductDTO addProduct(ProductDTO product){
        return productRepository.save(product);
    }

    /**
         * This method remove a product filtered by it's id
         * @param id 
         */
    public void deleteProduct(Integer id){
        productRepository.deleteById(id);        
    }

    /**
     * This method updates the product in the list
     * @param product the new product
     * @return the product that was added
     */
    public ProductDTO updateProduct(ProductDTO product, Integer id){
        product.setId(id);
        return productRepository.save(product);
    }    
    
}
