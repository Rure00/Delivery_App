package com.delivery.app.Delivery.controller;

import com.delivery.app.Delivery.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) { this.service = service; }

    @PostMapping("/save")
    public void saveReview() {

    }

    @PostMapping("/mine")
    public void getMyReviews() {

    }

    @PostMapping("/get")
    public void getReviews() {

    }

    @PostMapping("/remove")
    public void removeReview() {

    }
}
