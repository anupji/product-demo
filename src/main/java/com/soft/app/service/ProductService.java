package com.soft.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft.app.model.APISuccessResponseModel;
import com.soft.app.constant.AppConstants;
import com.soft.app.entity.Product;
import com.soft.app.model.ProductApiRequest;
import com.soft.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<Object> saveProduct(Product product) {
        // Logic to save the product
        Product saved = productRepository.save(product);
        ResponseEntity<Object> responseEntity = getObjectResponseEntity(saved, "Product saved successfully with id: " + saved.getId());
        return responseEntity;
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public ResponseEntity<Object> updateProduct(ProductApiRequest product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if(optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            ResponseEntity<Object> responseEntity = getObjectResponseEntity(existingProduct, "Product updated successfully with id: " + existingProduct.getId());

            return responseEntity;
            //return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found with id: " + product.getId());
        }
    }

    private ResponseEntity<Object> getObjectResponseEntity(Product existingProduct, String message) {
        JsonNode responseNode = objectMapper.valueToTree(existingProduct);
        APISuccessResponseModel model = new APISuccessResponseModel();
        model.setSuccess(Boolean.TRUE);
        model.setMessage(message);
        model.setData(responseNode);
        ResponseEntity<Object> responseEntity= new ResponseEntity<>(model, HttpStatus.OK);
        return responseEntity;
    }
}
