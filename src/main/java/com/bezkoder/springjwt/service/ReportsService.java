package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.OrdersReport;
import com.bezkoder.springjwt.models.UsersReport;
import com.bezkoder.springjwt.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {

    @Autowired
    ReportsRepository reportsRepository;

    public List<OrdersReport> getAllOrders() {
        return reportsRepository.getAllOrders();
    }

    public List<UsersReport> getAllUsers() {
        return reportsRepository.getAllUsers();
    }
}
