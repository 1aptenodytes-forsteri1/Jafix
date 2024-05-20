package com.example.demo.Models;

import java.util.HashMap;
import java.util.Map;

public class StandardRecipe {
    private Integer standard_recipe_id;
    private String name;
    private Double price;
    private String image;

    private Map<String,String> components = new HashMap<String,String>();
    public StandardRecipe(Integer standard_recipe_id, String name, Double price, String image) {
        this.standard_recipe_id = standard_recipe_id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public void addComponent(String name, String amount){
        components.put(name,amount);
    }
    public Map<String, String> getComponents() {
        return components;
    }
    public Integer getStandard_recipe_id() {
        return standard_recipe_id;
    }

    public void setStandard_recipe_id(Integer standard_recipe_id) {
        this.standard_recipe_id = standard_recipe_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
