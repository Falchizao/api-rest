package com.example.posbanco.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.posbanco.model.Product;
import com.example.posbanco.services.ProductService;
import com.example.posbanco.shared.ProductDTO;
import com.example.posbanco.view.model.ProductResponse;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){ //Here the method return is a list of ProductResponse, so we need to cast the list 
        List<ProductDTO> products = productService.getProducts();       
        //The response Entity is a spring tool that allows the code to return a http status plus the object as the response
        return new ResponseEntity<>(products.stream().map(productDTO -> new ModelMapper().map(productDTO, ProductResponse.class)).collect(Collectors.toList()), HttpStatus.OK); 
    }

    @PostMapping
    public ProductResponse addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id){
        Optional<ProductDTO> dto = productService.getProductById(id);

        ModelMapper map = new ModelMapper();
        ProductResponse productResponse = map.map(dto, ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "deleted with sucess";
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@RequestBody Product product, @PathVariable Integer id){
        return productService.updateProduct(product, id);
    }
    
}
