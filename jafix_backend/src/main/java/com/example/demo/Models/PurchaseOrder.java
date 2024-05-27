package com.example.demo.Models;

public class PurchaseOrder {
    Integer id;
    Double cost;
    Integer coffeeHouseId;
    Integer userId;
    Boolean isActive;
    Purchase purchase;
    Order order;

    public PurchaseOrder(Integer id, Double cost, Integer coffeeHouseId,Boolean isActive, Purchase purchase, Order order, Integer userId) {
        this.id = id;
        this.cost = cost;
        this.coffeeHouseId = coffeeHouseId;
        this.isActive = isActive;
        this.purchase = purchase;
        this.order = order;
        this.userId = userId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    public Integer getCoffeeHouseId() {
        return coffeeHouseId;
    }

    public void setCoffeeHouseId(Integer coffeeHouseId) {
        this.coffeeHouseId = coffeeHouseId;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
