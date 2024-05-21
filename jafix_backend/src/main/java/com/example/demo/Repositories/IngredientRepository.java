package com.example.demo.Repositories;

import com.example.demo.Models.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientRepository {
    private final JdbcTemplate jdbcTemplate;
    RowMapper<Ingredient> rowMapper = (r,i)->{
        return new Ingredient(
                r.getInt("ingredient_id"),
                r.getString("name"),
                r.getDouble("price"),
                r.getString("units")
        );
    };
    IngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Ingredient> getIngredients(){
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql,rowMapper);
    }

}
