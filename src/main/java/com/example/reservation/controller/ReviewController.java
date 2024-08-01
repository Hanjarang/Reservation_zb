package com.example.reservation.controller;

import com.example.reservation.dto.ReviewDto;
import com.example.reservation.model.Review;
import com.example.reservation.service.ReviewService;
import com.example.reservation.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public void addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.addReview(reviewDto.getReservationId(), reviewDto.getUserId(), reviewDto.getText());
    }

    @GetMapping("/reservation/{reservationId}")
    public List<Review> getReviewsByReservation(@PathVariable Long reservationId) {
        return reviewService.getReviewsByReservation(reservationId);
    }

    @GetMapping("/list/{storeId}")
    public ResponseEntity<Object> getReviewsByStoreId(@PathVariable Long storeId) {
        List<Review> reviews = reviewService.getReviewsByStore(storeId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(404).body("해당 가게에 대한 리뷰가 없습니다.");
        }
        return ResponseEntity.ok(reviews);
    }


}