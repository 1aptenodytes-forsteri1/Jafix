package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ProductOrder {

    Integer id;
    Integer productId;
    Integer purchaseId;
    String product;

    public ProductOrder(Integer id, Integer productId, Integer purchaseId, String product) {
        this.id = id;
        this.productId = productId;
        this.purchaseId = purchaseId;
        this.product = product;
    }
    @JsonCreator
    public ProductOrder(Integer productId) {
        this.productId = productId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
