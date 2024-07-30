package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDto;
import com.example.reservation.model.Review;
import com.example.reservation.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public void addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.addReview(reviewDto.getReservationId(), reviewDto.getUserId(), reviewDto.getContent());
    }

    @GetMapping("/{reservationId}")
    public List<Review> getReviewsByReservation(@PathVariable Long reservationId) {
        return reviewService.getReviewsByReservation(reservationId);
    }

    @GetMapping("/{storeId}")
    public List<Review> getReviewsByStore(@PathVariable Long storeId) {
        return reviewService.getReviewsByStore(storeId);
    }
}
