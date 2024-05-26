package com.example.demo.Models;

import java.util.Map;

public class CustomRecipe{

    private Integer id;
    private Integer userId;
    private Double cost;
    private String name;
    private Map<String, String> components;

    public CustomRecipe(Integer id, Integer userId, Double cost, String name) {
        this.id = id;
        this.userId = userId;
        this.cost = cost;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
