package com.example.demo.Models;

public class CustomRecipeComponent {
    private Integer component_id;
    private Integer custom_recipe_id;
    private String ingredient;
    private String amount;

    public CustomRecipeComponent(Integer component_id, Integer custom_recipe_id, String ingredient, String amount) {
        this.component_id = component_id;
        this.custom_recipe_id = custom_recipe_id;
        this.ingredient = ingredient;
        this.amount = amount;
    }
    public Integer getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Integer component_id) {
        this.component_id = component_id;
    }

    public Integer getCustom_recipe_id() {
        return custom_recipe_id;
    }

    public void setCustom_recipe_id(Integer custom_recipe_id) {
        this.custom_recipe_id = custom_recipe_id;
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

}
