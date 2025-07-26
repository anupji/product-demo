package com.soft.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<Object> saveProduct(@RequestBody ProductApiRequestBody request) {
        // Logic to save the product
        ProductApiRequest data = Optional.ofNullable(request.getData()).orElse(new ProductApiRequest());
        JsonNode responseNode = null;
        Long id = Optional.ofNullable(data.getId()).orElse(0L);
        String name = Optional.ofNullable(data.getName()).orElse("");
        Integer quantity = Optional.ofNullable(data.getQuantity()).orElse(0);
        Double price = Optional.ofNullable(data.getPrice()).orElse(0.0);

        if(id==0 || name.isEmpty() || quantity==0 || price==0.0) {
            LOGGER.error(AppConstants.INPUT_VAL);
            return responseUtil.setFailureResponseStatus(AppConstants.INPUT_VAL, HttpStatus.OK,AppConstants.BAD_REQUEST);
        }
        Product product = new Product();
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        ResponseEntity<Object> responseObject = productService.saveProduct(product);
        Result result = getResult(responseObject);
        return new ResponseEntity<>(result.responseDataNode(), result.returnStatus());
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

        if(id==0 || name.isEmpty() || quantity==0 || price==0.0) {
            LOGGER.error(AppConstants.INPUT_VAL);
            return responseUtil.setFailureResponseStatus(AppConstants.INPUT_VAL, HttpStatus.OK,AppConstants.BAD_REQUEST);
        }

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

        Result result = getResult(responseObject);

        return new ResponseEntity<>(result.responseDataNode(), result.returnStatus());
    }

    private Result getResult(ResponseEntity<Object> responseObject) {
        Object responseBody = responseObject.getBody();
        HttpStatusCode status = responseObject.getStatusCode();

        HttpStatus returnStatus = HttpStatus.valueOf(status.value());
        JsonNode responseDataNode = objectMapper.valueToTree(responseBody);
        Result result = new Result(returnStatus, responseDataNode);
        return result;
    }

    private record Result(HttpStatus returnStatus, JsonNode responseDataNode) {
    }
}
