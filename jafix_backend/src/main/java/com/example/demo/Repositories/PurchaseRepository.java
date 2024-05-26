package com.example.demo.Repositories;

import com.example.demo.Models.ProductOrder;
import com.example.demo.Models.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class PurchaseRepository {
    private final JdbcTemplate jdbcTemplate;
    PurchaseRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<Purchase> purchaseRowMapper = (r,i)->{
        return new Purchase(
                r.getInt("purchase_id"),
                r.getDouble("cost"),
                r.getInt("user_id"),
                r.getTimestamp("time").toLocalDateTime().toString()
        );
    };
    private final RowMapper<ProductOrder> productOrderRowMapper = (r,i)->{
        return new ProductOrder(
                r.getInt("product_order_id"),
                r.getInt("product_id"),
                r.getInt("purchase_id"),
                r.getString("name")
        );
    };
    private Purchase getLatestPurchase(){
        String sql = "SELECT * FROM purchase WHERE purchase_id = (SELECT MAX(purchase_id) FROM purchase)";
        return jdbcTemplate.query(sql,purchaseRowMapper).get(0);
    }
    private List<ProductOrder> getProductOrders (Integer purchaseId){
        String sql = "SELECT product_order_id,product_order.product_id,purchase_id,name FROM product_order INNER JOIN product ON product_order.product_id = product.product_id ";
        return jdbcTemplate.query(sql,productOrderRowMapper);
    }
    @Transactional
    public void makePurchase(Purchase purchase){
        Purchase result = purchase;
        String sql = "INSERT INTO purchase VALUES (NULL,?,?,?)";
        LocalDateTime localDateTime = LocalDateTime.parse(purchase.getTime());
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        jdbcTemplate.update(sql,purchase.getCost(),purchase.getUserId(),timestamp);
        for(ProductOrder productOrder : purchase.getProductOrders()){
            String insertProduct = "INSERT INTO product_order VALUES(NULL,?,?)";
            jdbcTemplate.update(insertProduct,productOrder.getProductId(),getLatestPurchase().getId());
        }
    }
    public List<Purchase> getActivePurchases(){
        String sql = "SElECT * FROM purchase WHERE active = 1";
        List<Purchase> result = jdbcTemplate.query(sql,purchaseRowMapper);
        for(Purchase purchase : result){
            purchase.setProductOrders(getProductOrders(purchase.getId()));
        }
        return result;
    }
    public void completePurchase(Integer purchaseId){
        String sql = "UPDATE purchase SET active = 0 WHERE purchase_id = ?;";
        jdbcTemplate.update(sql,purchaseId);
    }
}
