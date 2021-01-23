package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.OrdersReport;
import com.bezkoder.springjwt.models.UsersReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportsRepository {

    @Autowired
    JdbcTemplate template;

    public List<OrdersReport> getAllOrders() {
        String sql = "SELECT DISTINCT orders.id AS order_id, users.id AS user_id, users.username, users.email, \n" +
                "     roles.name AS role, SUM(course.price) AS total_price_for_courses, orders.date, GROUP_CONCAT(course.title SEPARATOR ',   ') as courses\n" +
                "                FROM orders\n" +
                "                LEFT JOIN order_line\n" +
                "                ON orders.id = order_line.order_id\n" +
                "                LEFT JOIN users\n" +
                "                ON orders.user_id = users.id\n" +
                "                LEFT JOIN course\n" +
                "                ON order_line.course_id = course.id\n" +
                "                LEFT JOIN user_roles\n" +
                "                ON users.id = user_roles.user_id\n" +
                "                LEFT JOIN roles\n" +
                "                ON user_roles.role_id = roles.id\n" +
                "                GROUP BY order_id;\n";
        RowMapper<OrdersReport> rowMapper = new BeanPropertyRowMapper<>(OrdersReport.class);
        return template.query(sql, rowMapper);
    }

    public List<UsersReport> getAllUsers() {
        String sql = "SELECT DISTINCT users.id AS user_id, users.username, users.email, roles.name AS role\n" +
                "                FROM users\n" +
                "                LEFT JOIN user_roles\n" +
                "                ON users.id = user_roles.user_id\n" +
                "                LEFT JOIN roles\n" +
                "                ON user_roles.role_id = roles.id\n" +
                "                ORDER BY user_id;";
        RowMapper<UsersReport> rowMapper = new BeanPropertyRowMapper<>(UsersReport.class);
        return template.query(sql, rowMapper);
    }





//    public OrdersReport findOrderReportById(int id) {
//        String sql ="SELECT * FROM motorhomes WHERE motor_id = ?";
//        RowMapper<OrdersReport> rowMapper = new BeanPropertyRowMapper<>(OrdersReport.class);
//        OrdersReport ordersReport = template.queryForObject(sql, rowMapper, id);
//        return ordersReport;
//    }
}
