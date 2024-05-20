package com.example.demo.Models;

import java.util.HashMap;
import java.util.Map;

public class CustomRecipe {
    private Integer userId;
    private Double cost;
    private String name;
    private Map<String, String> components;

    public CustomRecipe(Integer userId, Double cost, String name, Map<String, String> components) {
        this.userId = userId;
        this.cost = cost;
        this.name = name;
        this.components = components;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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
