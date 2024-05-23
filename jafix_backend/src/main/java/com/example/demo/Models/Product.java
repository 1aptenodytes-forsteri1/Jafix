package com.example.demo.Models;

import java.util.Map;

public class Product {
    private Integer id;
    private Double price;
    private String name;
    private Map<String,String> components;

    public Product(Integer id, Double price, String name, Map<String, String> components) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.components = components;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getComponents() {
        return components;
    }

    public void setComponents(Map<String, String> components) {
        this.components = components;
    }
}
