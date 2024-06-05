package com.example.demo.Repositories;

import com.example.demo.Models.Batch;
import com.example.demo.Models.ProductComponent;
import com.example.demo.Models.ProductOrder;
import com.example.demo.Models.Purchase;
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
                r.getInt("total"),
                r.getDate("expiration").toString()
        );
    };
    private final RowMapper<Integer> idRowMapper = (r,i)->r.getInt("ingredient_id");
    private final RowMapper<Integer> batchIdRowMapper = (r,i)->r.getInt("batch_id");

    private final JdbcTemplate jdbcTemplate;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    BatchRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository,IngredientRepository ingredientRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
    }
    private Integer getIdByIngredient(String ingredient){
        String sql = String.format("SELECT ingredient_id FROM ingredient WHERE name = \"%s\"",ingredient);
        return jdbcTemplate.query(sql,idRowMapper).get(0);
    }
    public List<Batch> getBatchesByCoffeeHouse(Integer coffeeHouseId){
        String sql = "SELECT batch_id, coffe_house_id, name, SUM(amount) as total, expiration FROM batch INNER JOIN ingredient ON batch.ingredient_id = ingredient.ingredient_id WHERE coffe_house_id = ? GROUP BY name HAVING total < 10000;";
        return jdbcTemplate.query(sql,batchRowMapper,coffeeHouseId);
    }
    public void addBatch(Batch batch){
        String sql = "INSERT INTO batch VALUES (NULL,?,?,?,?)";
        jdbcTemplate.update(sql,batch.getCoffee_house_id(),getIdByIngredient(batch.getIngredient()),batch.getAmount(),batch.getExpirationDate());
    }
    public void spendIngredient(Purchase purchase){
        String sql = "UPDATE batch SET amount = amount - ? WHERE batch_id = ?;";
        for (ProductOrder productOrder : purchase.getProductOrders()){
            
        }
    }
    private Integer getProperBatchId(Integer coffeeHouseId, String ingredient){
        Integer ingredientId = getIdByIngredient(ingredient);
        String sql = "SELECT batch_id FROM batch WHERE ingredient_id = ? AND expiration = (SELECT MIN(expiration) FROM batch WHERE ingredient_id = ?) AND coffe_house_id = ?;";
        return jdbcTemplate.query(sql, batchIdRowMapper, ingredientId, ingredientId, coffeeHouseId).get(0);
    }
}
