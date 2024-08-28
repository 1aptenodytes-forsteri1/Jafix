package com.example.demo.Repositories;

import com.example.demo.Models.Order;
import com.example.demo.Models.Purchase;
import com.example.demo.Models.PurchaseOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PurchaseOrderRepository {
    private  final JdbcTemplate jdbcTemplate;
    private OrderRepository orderRepository;
    private PurchaseRepository purchaseRepository;
    private final RowMapper<PurchaseOrder> purchaseOrderRowMapper = (r,i)->{
        return new PurchaseOrder(
                r.getInt("purchase_order_id"),
                r.getDouble("cost"),
                r.getInt("coffe_house_id"),
                r.getBoolean("active"),
                purchaseRepository.getPurchaseById(r.getInt("purchase_id")),
                orderRepository.getOrderById(r.getInt("customers_order_id")),
                r.getInt("user_id")
        );
    };
    PurchaseOrderRepository(JdbcTemplate jdbcTemplate, PurchaseRepository purchaseRepository, OrderRepository orderRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.purchaseRepository = purchaseRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void makeOrder(PurchaseOrder purchaseOrder){
        String sql = "INSERT INTO purchase_order VALUES (NULL,?,?,?,?,?,1)";
        Order order = purchaseOrder.getOrder();
        Purchase purchase = purchaseOrder.getPurchase();
        purchase.setUserId(purchaseOrder.getUserId());
        order.setUserId(purchaseOrder.getUserId());
        purchase.setCost(purchaseOrder.getCost());
        order.setCost(purchaseOrder.getCost());
        orderRepository.makeOrder(order);
        purchaseRepository.makePurchase(purchase);
        jdbcTemplate.update(sql,purchaseOrder.getCost(),purchaseOrder.getCoffeeHouseId(),purchaseRepository.getLatestPurchase().getId(),orderRepository.getLatestOrder().getOrderId(),purchaseOrder.getUserId());
    }
    public List<PurchaseOrder> getOrdersByUserId(Integer id){
        String sql = "SELECT * FROM purchase_order WHERE user_id = ?";
        List<PurchaseOrder> purchaseOrders = jdbcTemplate.query(sql,purchaseOrderRowMapper,id);
        for (PurchaseOrder purchaseOrder : purchaseOrders){
            purchaseOrder.setPurchase(purchaseRepository.getPurchaseById(purchaseOrder.getPurchase().getId()));
            purchaseOrder.setOrder(orderRepository.getOrderById(purchaseOrder.getOrder().getOrderId()));
        }
        return purchaseOrders;
    }
    public PurchaseOrder getLatest(){
        String sql = "SELECT * FROM purchase_order WHERE purchase_order_id = (SELECT MAX(purchase_order_id) FROM purchase_order)";
        List<PurchaseOrder> purchaseOrders = jdbcTemplate.query(sql,purchaseOrderRowMapper);
        for (PurchaseOrder purchaseOrder : purchaseOrders){
            purchaseOrder.setPurchase(purchaseRepository.getPurchaseById(purchaseOrder.getPurchase().getId()));
            purchaseOrder.setOrder(orderRepository.getOrderById(purchaseOrder.getOrder().getOrderId()));
        }
        return purchaseOrders.get(0);
    }
    public List<PurchaseOrder> getActiveOrders(Integer id){
        String sql = "SELECT * FROM purchase_order WHERE active = 1 AND coffe_house_id = ?";
        List<PurchaseOrder> purchaseOrders = jdbcTemplate.query(sql,purchaseOrderRowMapper,id);
        for (PurchaseOrder purchaseOrder : purchaseOrders){
            purchaseOrder.setPurchase(purchaseRepository.getPurchaseById(purchaseOrder.getPurchase().getId()));
            purchaseOrder.setOrder(orderRepository.getOrderById(purchaseOrder.getOrder().getOrderId()));
        }
        return purchaseOrders;
    }
    @Transactional
    public void deactivateOrder(Integer id){
        String sql = "UPDATE purchase_order SET active = 0 WHERE purchase_order_id = ?";
        jdbcTemplate.update(sql,id);

    }
    public void activateOrder(Integer id){
        String sql = "UPDATE purchase_order SET active = 1 WHERE purchase_order_id = ?";
        jdbcTemplate.update(sql,id);
    }
}
