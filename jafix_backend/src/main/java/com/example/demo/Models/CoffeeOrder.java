package com.example.demo.Models;

public class CoffeeOrder {


    private Integer id;
    private Integer customRecipeId;
    private Integer orderId;
    private Integer standardRecipeId;

    public CoffeeOrder(Integer id, Integer customRecipeId, Integer orderId, Integer standardRecipeId) {
        this.id = id;
        this.customRecipeId = customRecipeId;
        this.orderId = orderId;
        this.standardRecipeId = standardRecipeId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomRecipeId() {
        return customRecipeId;
    }

    public void setCustomRecipeId(Integer customRecipeId) {
        this.customRecipeId = customRecipeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStandardRecipeId() {
        return standardRecipeId;
    }

    public void setStandardRecipeId(Integer standardRecipeId) {
        this.standardRecipeId = standardRecipeId;
    }
}
