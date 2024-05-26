package com.example.demo.Models;

public class Ingredient {
    private Integer id;
    private String name;
    private Double price;
    private String units;
    private String description;
    public Ingredient(Integer id, String name, Double price, String units, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.units = units;
        this.description = description;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
