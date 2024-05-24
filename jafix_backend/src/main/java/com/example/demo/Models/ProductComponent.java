package com.example.demo.Models;

public class ProductComponent {
    private Integer id;
    private Integer batch_id;
    private Integer product_id;
    private String amount;
    private String ingredient;

    public ProductComponent(Integer id, Integer batch_id, Integer product_id, String amount, String ingredient) {
        this.id = id;
        this.batch_id = batch_id;
        this.product_id = product_id;
        this.amount = amount;
        this.ingredient = ingredient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
