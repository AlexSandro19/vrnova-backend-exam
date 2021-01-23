package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{
}
