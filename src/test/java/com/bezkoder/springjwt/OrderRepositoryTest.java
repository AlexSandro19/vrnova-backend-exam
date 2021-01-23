package com.bezkoder.springjwt;

import com.bezkoder.springjwt.models.Order;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void init() {
        orderRepository.deleteAll();
    }

    @Test
    public void should_find_no_orders_if_repository_is_empty() {
        List<Order> orderList = orderRepository.findAll();
        assertThat(orderList).isEmpty();
    }

    @Test
    public void should_save_an_order() {
        User user = new User("Name", "email@gmail.com", "123456");
        entityManager.persist(user);
        LocalDate now = LocalDate.now();
        Order order = new Order(now, user);
        Order savedOrder = orderRepository.save(order);
        System.out.println("SAVED ORDER:  " + savedOrder);
        assertEquals(savedOrder, order);
    }

    @Test
    public void should_find_all_orders() {
        User user = new User("Name", "email@gmail.com", "123456");
        entityManager.persist(user);
        LocalDate now = LocalDate.now();
        Order order = new Order(now, user);
        Order savedOrder = orderRepository.save(order);
        Iterable<Order> orders = orderRepository.findAll();
        assertThat(orders).hasSize(1).contains(savedOrder);
    }
}

