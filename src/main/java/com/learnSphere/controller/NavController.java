package com.learnSphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registor() {
        return "register";
    }

    @GetMapping("/register_pop_up")
    public String register_pop_up() {
        return "register_pop_up";
    }

    @GetMapping("/contact_pop_up")
    public String contact_pop_up() {
        return "contact_pop_up";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/createCourse")
    public String createCourse() {
        return "createCourse";
    }

    @GetMapping("/addLesson")
    public String addLesson() {
        return "addLesson";
    }

    @GetMapping("/trainerHome")
    public String trainerHome() {
        return "trainerHome";
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/studentHome")
    public String studentHome() {
        return "studentHome";
    }

//    @GetMapping("/purchaseCourse")
//    public String purchasse() {
//        return "purchaseCourse";
//    }
//
//    @GetMapping("/payment-failure")
//    public String paymentFailure() {
//        return "payment-failure";
//    }
//
//    @GetMapping("/payment-success")
//    public String paymentSuccess() {
//        return "payment-Success";
//    }

}  
