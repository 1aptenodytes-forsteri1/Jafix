package com.example.demo.Models;

import java.util.HashMap;
import java.util.Map;

public class StandardRecipe{
    private Integer id;
    private String name;
    private Double price;
    private String image;
    private Map<String,String> components = new HashMap<String,String>();
    public StandardRecipe(Integer id, String name, Double price, String image) {
        this.id = id;
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
    public void setComponents(Map<String, String> components) {
        this.components = components;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
