package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.exception.ResourceNotFoundException;
import com.bezkoder.springjwt.models.OrderLine;
import com.bezkoder.springjwt.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    OrderLineRepository orderLineRepository;

    public List<OrderLine> findAll(){
        return orderLineRepository.findAll();
    }

    public OrderLine findById(Long id){
        return orderLineRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Line not found"));
    }

    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }
}
