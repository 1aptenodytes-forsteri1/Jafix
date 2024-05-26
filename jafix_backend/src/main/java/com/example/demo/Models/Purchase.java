package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class Purchase {
    Integer id;
    Double cost;
    Integer userId;
    String time;
    Boolean isActive;
    List<ProductOrder> productOrders;

    @JsonCreator
    public Purchase(Integer id, Double cost, Integer userId, String time,List<ProductOrder> productOrders) {
        this.id = id;
        this.cost = cost;
        this.userId = userId;
        this.time = time;
        this.isActive = true;
        this.productOrders = productOrders;
    }
    public Purchase(Integer id, Double cost, Integer userId, String time) {
        this.id = id;
        this.cost = cost;
        this.userId = userId;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}
