package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.dto.CourseDto;
import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.service.CourseService;
import com.bezkoder.springjwt.service.OrderLineService;
import com.bezkoder.springjwt.service.OrderService;
import com.bezkoder.springjwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

    private UserService userService;
    private OrderService orderService;
    private OrderLineService orderLineService;
    private CourseService courseService;

    public OrderController(UserService userService, OrderService orderService, OrderLineService orderLineService, CourseService courseService) {
        this.userService = userService;
        this.orderService = orderService;
        this.orderLineService = orderLineService;
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {

        List<CourseDto> formDtos = form.getItems();
        //validateProductsExistence(formDtos);
        Order order = new Order();
        //order.setStatus(OrderStatus.PAID.name());

        // save customer first because order needs customer id in order to get saved.
        int userId = form.getUserId();
        User user = userService.findById((long) userId);
        order.setUser(user);

        orderService.save(order);

        List<OrderLine> orderLines = new ArrayList<>();
        for (CourseDto dto : formDtos) {
            orderLines.add(orderLineService.save(new OrderLine(order,
                    courseService.findById(dto.getId()), 0)));
        }

        order.setOrderLines(orderLines);

        this.orderService.update(order);
/*

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
*/

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }



    public static class OrderForm {

        private List<CourseDto> items;

        private int userId;

        public List<CourseDto> getItems() {
            return items;
        }

        public void setItems(List<CourseDto> items) {
            this.items = items;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    @PostMapping("my-courses")
    public ResponseEntity<List<CourseDto>> getMyCourses(@RequestBody UserId userId) {
        User user = userService.findById((long) userId.getUserId());
        List<Order> orderList = orderService.findByUser(user);
        System.out.println("orderList ==== " + orderList);
        List<OrderLine> orderLines = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        List<Object> progress = new ArrayList<>();
        List<CourseDto> courseDtos = new ArrayList<>();

        for (Order order: orderList) {
            orderLines.addAll(order.getOrderLines());
        }

        for (OrderLine orderLine : orderLines) {
            courses.add(orderLine.getCourse());
            progress.add(orderLine.getProgress());
        }

        System.out.println("Progress -----> " + progress);

        for (Course course : courses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(course.getId());
            courseDto.setCategoryNames(course.getCategoryNames());
            courseDto.setTeacher(course.getTeacher());
            courseDto.setImage(course.getImage());
            courseDto.setPrice(course.getPrice());
            courseDto.setDescription(course.getDescription());
            courseDto.setDuration(course.getDuration());
            courseDto.setTitle(course.getTitle());
            courseDto.setVideoUrls(course.getVideos().stream().map(Video::getVideoUrl).collect(Collectors.toList()));
            courseDtos.add(courseDto);
        }

        for (int i = 0; i < courseDtos.size(); i++) {
            courseDtos.get(i).setProgress((Integer) progress.get(i));
        }

        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        System.out.println("courses ====> " + courses);
        System.out.println("courseDtos =====> " + courseDtos);

        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }

    @PostMapping("save-progress")
    public ResponseEntity<?> saveProgress(@RequestBody SaveProgressDto saveProgressDto) {
        User user = userService.findById((long) saveProgressDto.getUserId());
        Set<Order> orderList = user.getOrders();
        System.out.println("Orders for this user =>>>> " + orderList);
        // find the order which contains the given course
        Course course = courseService.findById((long) saveProgressDto.getCourseId());
        System.out.println("the given course= " + course);
        List<OrderLine> newOrderLines = new ArrayList<>();

        for (Order order : orderList) {
            List<OrderLine> orderLines = order.getOrderLines();
            for (OrderLine orderLine : orderLines) {
                System.out.println("Print Course ====> " + orderLine.getCourse());
                System.out.println(orderLine.getCourse().equals(course));
                if (orderLine.getCourse() == course) {
                    System.out.println("inside the if!!!");
                    orderLine.setProgress(saveProgressDto.getProgress());
                }
                newOrderLines.add(orderLine);
            }
            order.setOrderLines(newOrderLines);
            // update the order
            orderService.update(order);
        }

        return new ResponseEntity<>("Progress saved.", HttpStatus.OK);
    }

    public static class UserId {

        private int userId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }

    public static class SaveProgressDto {

        private int userId;

        private int courseId;

        private int progress;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}
