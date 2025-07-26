package com.soft.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.soft.app.constant.AppConstants;
import com.soft.app.entity.Product;
import com.soft.app.model.ProductApiRequest;
import com.soft.app.model.ProductApiRequestBody;
import com.soft.app.service.ProductService;
import com.soft.app.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ResponseUtil responseUtil;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Product API!";
    }

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        // Logic to save the product
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Logic to save the product
        Product existingProduct = productService.getProductById(id);
        return ResponseEntity.ok(existingProduct);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductApiRequestBody request) {
        // Logic to update the product

        ProductApiRequest data = Optional.ofNullable(request.getData()).orElse(new ProductApiRequest());

        Long id = Optional.ofNullable(data.getId()).orElse(0L);
        String name = Optional.ofNullable(data.getName()).orElse("");
        Integer quantity = Optional.ofNullable(data.getQuantity()).orElse(0);
        Double price = Optional.ofNullable(data.getPrice()).orElse(0.0);

        JsonNode apiJsonRequest = objectMapper.valueToTree(data);
        String apiRequest = apiJsonRequest.toString();
        JsonNode searchVehicleResponse = null;
        JsonNode responseNode = null;
        try {

            //searchVehicleResponse= webclientCallToEnterprise.webClientCallToEnterpriseApi(searchVehicleApi,apiRequest);


        } catch (Exception e) {

            LOGGER.error("Exception occurred in while  webClientCallToEnterpriseApi :{}", e.getMessage());

            return responseUtil.setFailureResponseStatus(AppConstants.SEARCH_VEHICLE_ERROR, HttpStatus.OK,
                    responseNode);
        }

        ResponseEntity<Object> responseObject  = productService.updateProduct(data);

        Object responseBody = responseObject.getBody();
        HttpStatusCode status = responseObject.getStatusCode();

        HttpStatus returnStatus = HttpStatus.valueOf(status.value());
        JsonNode responseDataNode = objectMapper.valueToTree(responseBody);

        return new ResponseEntity<>(responseDataNode, returnStatus);
    }
}
