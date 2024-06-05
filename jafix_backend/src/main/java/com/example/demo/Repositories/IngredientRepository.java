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
                r.getString("units"),
                r.getString("description")
        );
    };
    RowMapper<Integer> idRowMapper = (r,i)->{
        return r.getInt("ingredient_id");
    };
    IngredientRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Ingredient> getIngredients(){
        String sql = "SELECT * FROM ingredient";
        return jdbcTemplate.query(sql,rowMapper);
    }
    public Integer getIdByName(String name){
        String sql = "SELECT ingredient_id FROM ingredient WHERE name = ?";
        return jdbcTemplate.query(sql, idRowMapper, name).get(0);
    }
}
