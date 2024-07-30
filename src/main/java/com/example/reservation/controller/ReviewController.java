package com.example.reservation.controller;

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
    public void addReview(@RequestParam Long reservationId,
                          @RequestParam Long userId,
                          @RequestParam String text) {
        reviewService.addReview(reservationId, userId, text);
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
