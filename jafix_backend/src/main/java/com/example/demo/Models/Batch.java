package com.example.demo.Models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Batch {
    private Integer id;
    private Integer coffee_house_id;
    private String ingredient;
    private Integer amount;
    private String expiration;

    public Batch(Integer id, Integer coffee_house_id, String ingredient, Integer amount, String expiration) {
        this.id = id;
        this.coffee_house_id = coffee_house_id;
        this.ingredient = ingredient;
        this.amount = amount;
        this.expiration = expiration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoffee_house_id() {
        return coffee_house_id;
    }

    public void setCoffee_house_id(Integer coffee_house_id) {
        this.coffee_house_id = coffee_house_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getExpiration() {
        return expiration.toString();
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public Date getExpirationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        localDate.plusMonths(3);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
