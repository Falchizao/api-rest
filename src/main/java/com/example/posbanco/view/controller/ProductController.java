package com.example.posbanco.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.posbanco.model.Product;
import com.example.posbanco.services.ProductService;
import com.example.posbanco.shared.ProductDTO;
import com.example.posbanco.view.model.ProductRequest;
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
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productReq){ //We get a request obj and return a response obj
        ModelMapper mapper = new ModelMapper(); 
        ProductDTO dto = mapper.map(productReq, ProductDTO.class); //Turn into dto to insert into db
        dto = productService.addProduct(dto);        
        
        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.CREATED); //Status 201 and cast again to return
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id){
        Optional<ProductDTO> dto = productService.getProductById(id);

        ModelMapper map = new ModelMapper();
        ProductResponse productResponse = map.map(dto, ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK); //Status 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest product, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();        
        ProductDTO dto = mapper.map(product, ProductDTO.class);
        dto = productService.updateProduct(dto, id);

        return new ResponseEntity<>(mapper.map(dto, ProductResponse.class), HttpStatus.OK);        
    }
    
}
