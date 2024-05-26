package com.example.demo.Controllers;

import com.example.demo.Models.CoffeeOrder;
import com.example.demo.Models.Order;
import com.example.demo.Repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderRepository orderRepository;
    OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public List<Order> getOrders(){
        return orderRepository.getOrders();
    }
    @PostMapping
    public void makeOrder(@RequestBody Order order){
        orderRepository.makeOrder(order);
    }
    @PatchMapping void completeOrder(@RequestBody Order order){
        orderRepository.completeOrder(order.getOrderId());
    }
}
