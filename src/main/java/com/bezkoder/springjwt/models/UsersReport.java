package com.bezkoder.springjwt.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsersReport {

    @Id
    private Long user_id;
    private String username;
    private String email;
    private String role;

    public UsersReport() {
    }

    public UsersReport(Long user_id, String username, String email, String role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.role = role;
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

    @Override
    public String toString() {
        return "UsersReport{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
