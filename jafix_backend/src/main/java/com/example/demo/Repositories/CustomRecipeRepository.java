package com.example.demo.Repositories;

import com.example.demo.Models.CustomRecipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class CustomRecipeRepository {
    private final RowMapper<Integer> ingredient_id = (r,i)->{
        return r.getInt("ingredient_id");
    };
    private final RowMapper<Integer> recipe_id = (r,i)->{
        return r.getInt("id");
    };
    private final RowMapper<Double> price = (r,i)->{
        return r.getDouble("price");
    };
    private final JdbcTemplate jdbcTemplate;
    CustomRecipeRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private Integer findId(String name){
        String sql = String.format("SELECT ingredient_id FROM ingredient WHERE name = \"%s\"",name);
        return jdbcTemplate.query(sql,ingredient_id).get(0);
    }
    private void addComponents(Map<String,String> components, Integer id){
        for (Map.Entry<String,String> entry : components.entrySet()){
            String sql = "INSERT INTO custom_recipe_component VALUES (NULL,?,?,?)";
            jdbcTemplate.update(sql,id,findId(entry.getKey()),Integer.parseInt(entry.getValue().replaceAll("\\D+", "")));
        }
    }
    private Integer getRecipeId(){
        String sql = "SELECT MAX(custom_recipe_id) AS id FROM custom_recipe";
        return jdbcTemplate.query(sql,recipe_id).get(0);
    }
    private Double getCost(Map<String,String> components){
        Double cost = 0.0;
        for (Map.Entry<String,String> entry : components.entrySet()){
            String sql = String.format("SELECT price FROM ingredient WHERE name = \"%s\" ",entry.getKey());
                cost += jdbcTemplate.query(sql,price).get(0) * Integer.parseInt(entry.getValue().replaceAll("\\D+", ""));
        }

        return Math.floor(cost * 100) / 100;
    }
    @Transactional
    public void addRecipe(CustomRecipe customRecipe){
        String sql = "INSERT INTO custom_recipe VALUES (NULL,?,?,?)";
        jdbcTemplate.update(sql,customRecipe.getUserId(),getCost(customRecipe.getComponents()),customRecipe.getName());
        addComponents(customRecipe.getComponents(),getRecipeId());
    }
}
