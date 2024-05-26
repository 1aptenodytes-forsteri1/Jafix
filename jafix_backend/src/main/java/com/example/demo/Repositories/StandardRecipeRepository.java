package com.example.demo.Repositories;

import com.example.demo.Models.StandardRecipe;
import com.example.demo.Models.StandardRecipeComponent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StandardRecipeRepository {
    private final JdbcTemplate jdbcTemplate;
    StandardRecipeRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    RowMapper<StandardRecipe> recipeRowMapper = (r,i)->{
        StandardRecipe standardRecipe = new StandardRecipe(
                r.getInt("standard_recipe_id"),
                r.getString("name"),
                r.getDouble("price"),
                r.getString("image")
        );
        return standardRecipe;
    };
    RowMapper<StandardRecipeComponent> componentRowMapper = (r,i)->{
        StandardRecipeComponent standardRecipeComponent = new StandardRecipeComponent(
                r.getInt("component_id"),
                r.getInt("standard_recipe_id"),
                r.getInt("batch_id"),
                r.getString("name"),
                r.getInt("amount") + r.getString("units")
        );
        return standardRecipeComponent;
    };
    public List<StandardRecipe> getAllRecipes(){
        List<StandardRecipe> standardRecipes = new ArrayList<>();
        String sql = "SELECT * FROM standard_recipe";
        for (StandardRecipe recipe : jdbcTemplate.query(sql, recipeRowMapper)){
            standardRecipes.add(recipe);
        }
        return standardRecipes;
    }
    public List<StandardRecipeComponent> getComponentsById(Integer id){
        List<StandardRecipeComponent> standardRecipes = new ArrayList<>();
        String sql = "SELECT component_id, standard_recipe_id, standard_recipe_component.batch_id, standard_recipe_component.amount, name, units FROM standard_recipe_component INNER JOIN batch ON standard_recipe_component.batch_id = batch.batch_id INNER JOIN ingredient ON batch.ingredient_id = ingredient.ingredient_id WHERE standard_recipe_id = ?; ";
        for (StandardRecipeComponent component : jdbcTemplate.query(sql, componentRowMapper, id)){
            standardRecipes.add(component);
        }
        return standardRecipes;
    }

    public StandardRecipe getRecipeById(Integer id){
        StandardRecipe standardRecipe;
        Map<String,String> components = new HashMap<>();
        String sql = "SELECT * FROM standard_recipe WHERE standard_recipe_id = ?";
        standardRecipe = jdbcTemplate.query(sql,recipeRowMapper,id).get(0);
        for (StandardRecipeComponent standardRecipeComponent : getComponentsById(id)){
            components.put(standardRecipeComponent.getIngredient(),standardRecipeComponent.getAmount());
        }
        standardRecipe.setComponents(components);
        return standardRecipe;
    }
}
