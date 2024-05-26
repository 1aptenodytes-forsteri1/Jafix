package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer userId;
    private Double cost;
    private String time;
    private Boolean isActive;
    private List<StandardRecipe> standardRecipes = new ArrayList<>();
    private List<CustomRecipe> customRecipes = new ArrayList<>();

    public Order(Integer orderId, Integer userId, Double cost, String time, Boolean isActive) {
        this.orderId = orderId;
        this.userId = userId;
        this.cost = cost;
        this.time = time;
        this.isActive = isActive;
    }

    @JsonCreator
    public Order(Integer orderId, Integer userId, Double cost, List<StandardRecipe> standardRecipes, List<CustomRecipe> customRecipes) {
        this.orderId = orderId;
        this.userId = userId;
        this.cost = cost;
        this.time = LocalDateTime.now().toString();
        this.standardRecipes = standardRecipes;
        this.customRecipes = customRecipes;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<StandardRecipe> getStandardRecipes() {
        return standardRecipes;
    }

    public void setStandardRecipes(List<StandardRecipe> standardRecipes) {
        this.standardRecipes = standardRecipes;
    }

    public List<CustomRecipe> getCustomRecipes() {
        return customRecipes;
    }

    public void setCustomRecipes(List<CustomRecipe> customRecipes) {
        this.customRecipes = customRecipes;
    }
    public void addCustomRecipe(CustomRecipe customRecipe){
        this.customRecipes.add(customRecipe);
    }
    public void addStandardRecipe(StandardRecipe standardRecipe){
        this.standardRecipes.add(standardRecipe);
    }
}
