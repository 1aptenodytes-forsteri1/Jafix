package com.example.demo.Models;

public class StandardRecipeComponent {
    private Integer component_id;
    private Integer standard_recipe_id;
    private Integer batch_id;
    private String ingredient;
    private String amount;

    public StandardRecipeComponent(Integer component_id, Integer standard_recipe_id,Integer batch_id, String ingredient, String amount) {
        this.component_id = component_id;
        this.standard_recipe_id = standard_recipe_id;
        this.batch_id = batch_id;
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }
    public Integer getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Integer component_id) {
        this.component_id = component_id;
    }

    public Integer getStandard_recipe_id() {
        return standard_recipe_id;
    }

    public void setStandard_recipe_id(Integer standard_recipe_id) {
        this.standard_recipe_id = standard_recipe_id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }





}
