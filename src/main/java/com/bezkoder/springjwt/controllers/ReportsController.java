package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CourseDto;
import com.bezkoder.springjwt.dto.PagingResponse;
import com.bezkoder.springjwt.models.CategoryCourse;
import com.bezkoder.springjwt.models.Course;
import com.bezkoder.springjwt.models.OrdersReport;
import com.bezkoder.springjwt.models.UsersReport;
import com.bezkoder.springjwt.service.CategoryService;
import com.bezkoder.springjwt.service.CourseService;
import com.bezkoder.springjwt.service.ReportsService;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/reports")
public class ReportsController {

    private ReportsService reportsService;


    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    //list of Orders for each User is returned. Format [{
    //        "order_id": 1,
    //        "user_id": 4,
    //        "username": "alex",
    //        "email": "a.sandrovschii@gmail.com",
    //        "role": "ROLE_USER",
    //        "total_price_for_courses": 400.0,
    //        "date": "2020-12-07"
    //    }]
    @GetMapping("/orders")
    public ResponseEntity<List<OrdersReport>> getOrdersReport() {
        List<OrdersReport> ordersReports = new ArrayList<>();
        ordersReports = reportsService.getAllOrders();
        return new ResponseEntity<>(ordersReports, HttpStatus.OK);
    }

    //list of Users is returned. Format [{
    //        "user_id": 1,
    //        "username": "user",
    //        "email": "rasraziel@gmail.com",
    //        "role": "ROLE_USER"
    //    }]
    @GetMapping("/users")
    public ResponseEntity<List<UsersReport>> getUsersReport() {
        List<UsersReport> usersReports = new ArrayList<>();
        usersReports = reportsService.getAllUsers();
        return new ResponseEntity<>(usersReports, HttpStatus.OK);
    }


}
