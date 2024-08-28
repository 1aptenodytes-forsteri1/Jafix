package com.example.demo.Repositories;

import com.example.demo.Models.Product;
import com.example.demo.Models.ProductComponent;
import com.example.demo.Models.StandardRecipe;
import com.example.demo.Models.StandardRecipeComponent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    ProductRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Product> productRowMapper = (r, i)->{
        Product product = new Product(
                r.getInt("product_id"),
                r.getDouble("price"),
                r.getString("name")
        );
        return product;
    };
    private final RowMapper<ProductComponent> componentRowMapper = (r, i)->{
        ProductComponent productComponent = new ProductComponent(
                r.getInt("element_id"),
                r.getInt("batch_id"),
                r.getInt("product_id"),
                r.getInt("amount") + r.getString("units"),
                r.getString("name")
        );
        return productComponent;
    };
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        for (Product product : jdbcTemplate.query(sql, productRowMapper)){
            for (ProductComponent productComponent : getComponentsById(product.getId())){
                product.addComponent(productComponent.getIngredient(),productComponent.getAmount());
            }
            products.add(product);
        }
        return products;
    }
    public List<ProductComponent> getComponentsById(Integer id){
        String sql = "SELECT element_id, product_element.batch_id, product_id, product_element.amount, name, units FROM product_element INNER JOIN batch ON product_element.batch_id = batch.batch_id INNER JOIN ingredient ON batch.ingredient_id = ingredient.ingredient_id WHERE product_id = ?;";
        return jdbcTemplate.query(sql, componentRowMapper, id);
    }
    public Product getProductById(Integer id){
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id = ?";
        for (Product product : jdbcTemplate.query(sql, productRowMapper, id)){
            for (ProductComponent productComponent : getComponentsById(product.getId())){
                product.addComponent(productComponent.getIngredient(),productComponent.getAmount());
            }
            products.add(product);
        }
        return products.get(0);
    }
}
