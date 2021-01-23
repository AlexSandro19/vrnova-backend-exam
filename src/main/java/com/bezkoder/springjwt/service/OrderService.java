package com.bezkoder.springjwt.service;


import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.models.Course;
import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public Order save(Order order) {
        order.setDate(LocalDate.now());
        return this.orderRepository.save(order);
    }

    public void update(Order order) {
        this.orderRepository.save(order);
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findOrdersByUserIsLike(user);
    }
}
