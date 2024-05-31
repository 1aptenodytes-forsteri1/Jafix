package com.example.demo.Repositories;

import com.example.demo.Models.Batch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BatchRepository {
    private final RowMapper<Batch> batchRowMapper = (r,i)->{
        return new Batch(
                r.getInt("batch_id"),
                r.getInt("coffe_house_id"),
                r.getString("name"),
                r.getInt("amount"),
                r.getDate("expiration").toString()
        );
    };
    private final RowMapper<Integer> idRowMapper = (r,i)->r.getInt("ingredient_id");

    private final JdbcTemplate jdbcTemplate;
    BatchRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private Integer getIdByIngredient(String ingredient){
        String sql = String.format("SELECT ingredient_id FROM ingredient WHERE name = \"%s\"",ingredient);
        return jdbcTemplate.query(sql,idRowMapper).get(0);
    }
    public List<Batch> getBatchesByCoffeeHouse(Integer coffeeHouseId){
        String sql = String.format("SELECT batch_id, coffe_house_id, name, SUM(amount) as total, expiration FROM batch INNER JOIN ingredient ON batch.ingredient_id = ingredient.ingredient_id WHERE coffe_house_id = 1 GROUP BY name HAVING total < 10000;",coffeeHouseId);
        return jdbcTemplate.query(sql,batchRowMapper);
    }
    public void addBatch(Batch batch){
        String sql = "INSERT INTO batch VALUES (NULL,?,?,?,?)";
        jdbcTemplate.update(sql,batch.getCoffee_house_id(),getIdByIngredient(batch.getIngredient()),batch.getAmount(),batch.getExpirationDate());
    }
}
