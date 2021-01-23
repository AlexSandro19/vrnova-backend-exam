package com.bezkoder.springjwt.models;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrdersReport {

    private Long order_id;
    private Long user_id;
    private String username;
    private String email;
    private String role;
    private double total_price_for_courses;
    private LocalDate date;
    private List<String> courses;

    public OrdersReport() {
    }

    public OrdersReport(Long order_id, Long user_id, String username, String email, String role, double total_price_for_courses, LocalDate date, List<String> courses) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.total_price_for_courses = total_price_for_courses;
        this.date = date;
        this.courses = courses;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getTotal_price_for_courses() {
        return total_price_for_courses;
    }

    public void setTotal_price_for_courses(double total_price_for_courses) {
        this.total_price_for_courses = total_price_for_courses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "OrdersReport{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", total_price_for_courses=" + total_price_for_courses +
                ", date=" + date +
                ", courses=" + courses +
                '}';
    }
}
