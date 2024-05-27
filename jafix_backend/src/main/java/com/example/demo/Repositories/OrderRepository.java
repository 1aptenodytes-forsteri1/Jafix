package com.example.demo.Repositories;

import com.example.demo.Models.CoffeeOrder;
import com.example.demo.Models.CustomRecipe;
import com.example.demo.Models.Order;
import com.example.demo.Models.StandardRecipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final StandardRecipeRepository standardRecipeRepository;
    private final CustomRecipeRepository customRecipeRepository;
    private final RowMapper<Order> orderRowMapper = (r,i)->{
        return new Order(
                r.getInt("order_id"),
                r.getInt("user_id"),
                r.getDouble("cost"),
                r.getTimestamp("time").toLocalDateTime().toString(),
                r.getBoolean("active")

        );
    };

    private final RowMapper<CoffeeOrder> coffeeOrderRowMapper = (r,i)->{
        return new CoffeeOrder(
                r.getInt("coffe_order_id"),
                r.getInt("custom_recipe_id"),
                r.getInt("order_id"),
                r.getInt("standard_recipe_id")
        );
    };
    OrderRepository(JdbcTemplate jdbcTemplate,StandardRecipeRepository standardRecipeRepository, CustomRecipeRepository customRecipeRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.standardRecipeRepository = standardRecipeRepository;
        this.customRecipeRepository = customRecipeRepository;

    }
    @Transactional
    public void makeOrder(Order order){
        String addOrder = "INSERT INTO customers_order VALUES(NULL,?,?,?,?)";
        String addStandard = "INSERT INTO coffee_order VALUES (NULL, NULL, ?,?)";
        String addCustom = "INSERT INTO coffee_order VALUES (NULL, ?,?,NULL)";
        LocalDateTime localDateTime = LocalDateTime.parse(order.getTime());
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        jdbcTemplate.update(addOrder,order.getUserId(),order.getCost(),timestamp,true);
        order.setOrderId(getLatestOrder().getOrderId());
        for (StandardRecipe standardRecipe : order.getStandardRecipes()){
            jdbcTemplate.update(addStandard,order.getOrderId(),standardRecipe.getId());
        }
        for (CustomRecipe customRecipe : order.getCustomRecipes()){
            jdbcTemplate.update(addCustom,customRecipe.getId(),order.getOrderId());
        }
    }
    public Order getLatestOrder(){
        String sql = "SELECT * FROM customers_order WHERE order_id = (SELECT MAX(order_id) FROM customers_order);";
        return jdbcTemplate.query(sql,orderRowMapper).get(0);
    }
    public List<Order> getOrders(){
        List<Order> orders;
        String getActiveOrders = "SELECT * FROM customers_order WHERE active = 1";
        String getCoffees = "SELECT * FROM coffee_order WHERE order_id = ?";
        orders = jdbcTemplate.query(getActiveOrders,orderRowMapper);
        for (Order order: orders){
            for (CoffeeOrder coffeeOrder: jdbcTemplate.query(getCoffees,coffeeOrderRowMapper,order.getOrderId())){
                if (coffeeOrder.getCustomRecipeId() != 0){
                    order.addCustomRecipe(customRecipeRepository.getRecipeByID(coffeeOrder.getCustomRecipeId()));
                }
                if (coffeeOrder.getStandardRecipeId() != 0){
                    order.addStandardRecipe(standardRecipeRepository.getRecipeById(coffeeOrder.getStandardRecipeId()));
                }
            }
        }
        return orders;
    }
    private List<CoffeeOrder> getCoffeesByOrderId(Integer id){
        String sql = "SELECT * FROM coffee_order WHERE order_id = ?";
        return jdbcTemplate.query(sql,coffeeOrderRowMapper,id);
    }
    public void completeOrder(Integer orderId){
        String sql = "UPDATE customers_order SET active = 0 WHERE order_id = ?;";
        jdbcTemplate.update(sql,orderId);
    }
    public Order getOrderById(Integer id){
        String sql = "SELECT * FROM customers_order WHERE order_id = ?";
        Order order = jdbcTemplate.query(sql,orderRowMapper,id).get(0);
        order.setCustomRecipes(getCustomRecipesByCoffeeOrders(getCoffeesByOrderId(id)));
        order.setStandardRecipes(getStandardRecipesByCoffeeOrders(getCoffeesByOrderId(id)));
        return order;
    }
    private List<CustomRecipe> getCustomRecipesByCoffeeOrders(List<CoffeeOrder> coffeeOrders){
        List<CustomRecipe> customRecipes = new ArrayList<>();
        for (CoffeeOrder coffeeOrder : coffeeOrders){
            if (coffeeOrder.getCustomRecipeId()>0){
                customRecipes.add(customRecipeRepository.getRecipeByID(coffeeOrder.getCustomRecipeId()));
            }
        }
        return customRecipes;
    }
    private List<StandardRecipe> getStandardRecipesByCoffeeOrders(List<CoffeeOrder> coffeeOrders){
        List<StandardRecipe> standardRecipes = new ArrayList<>();
        for (CoffeeOrder coffeeOrder : coffeeOrders){
            if (coffeeOrder.getStandardRecipeId()>0){
                standardRecipes.add(standardRecipeRepository.getRecipeById(coffeeOrder.getStandardRecipeId()));
            }
        }
        return standardRecipes;
    }
}
