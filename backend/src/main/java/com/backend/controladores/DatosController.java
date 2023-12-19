package com.backend.controladores;

import com.backend.modelo.ApiResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DatosController {
    
    //obtener nodo el modelo con datos
    
    @GetMapping("/external-data")
    public ApiResponse getAllProducts() {
        try {
            String apiUrl = "https://dummyjson.com/products";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
                ApiResponse response = objectMapper.convertValue(jsonResponse, ApiResponse.class);

                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse();
    }
    
    //Obtiene todos los productos
    @GetMapping("/external-data1")
    public List<ApiResponse.Product> getExternalDataDevulveProductos() {
        try {
            String apiUrl = "https://dummyjson.com/products";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
                JsonNode productsNode = jsonResponse.get("products");

                if (productsNode != null && productsNode.isArray()) {
                    List<ApiResponse.Product> products = new ArrayList<>();
                    for (JsonNode productNode : productsNode) {
                        ApiResponse.Product product = objectMapper.convertValue(productNode, ApiResponse.Product.class);
                        products.add(product);
                    }
                    
                    return products;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    
    @GetMapping("/external-data2")
    public void getExternalDataRecorre() {
        try {
            String apiUrl = "https://dummyjson.com/products";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
                JsonNode productsNode = jsonResponse.get("products");

                if (productsNode != null && productsNode.isArray()) {
                    for (JsonNode productNode : productsNode) {
                        ApiResponse.Product product = objectMapper.convertValue(productNode, ApiResponse.Product.class);
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("ID: " + product.getId());
                        System.out.println("Title: " + product.getTitle());
                        System.out.println("Description: " + product.getDescription());
                        System.out.println("Price: " + product.getPrice());
                        System.out.println("Discount Percentage: " + product.getDiscountPercentage());
                        System.out.println("Brand: " + product.getBrand());
                        System.out.println("Category: " + product.getCategory());
                        System.out.println("Rating: " + product.getRating());
                        System.out.println("Stock: " + product.getStock());
                        System.out.println("Thumbnail: " + product.getThumbnail());
                        System.out.println("Images: " + product.getImages());
                        System.out.println("-------------------------------------------------------------------");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //Devulve producto aleatorio
    @GetMapping("/external-data-3")
    public ApiResponse.Product getExternalDataDevuelveUno() {
        try {
            String apiUrl = "https://dummyjson.com/products";
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
                JsonNode productsNode = jsonResponse.get("products");

                if (productsNode != null && productsNode.isArray()) {
                    int numProducts = productsNode.size();
                    Random random = new Random();
                    int randomIndex = random.nextInt(numProducts);
                    
                    // Obtener el producto en el índice aleatorio
                    JsonNode randomProductNode = productsNode.get(randomIndex);
                    ApiResponse.Product randomProduct = objectMapper.convertValue(randomProductNode, ApiResponse.Product.class);
                    
                    System.out.println("producto random: ");
                    System.out.println("-----------------------------------------------: "+randomProduct.getTitle());
                    return randomProduct;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
