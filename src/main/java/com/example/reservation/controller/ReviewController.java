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

    // 리뷰 작성
    @PostMapping
    public void addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.addReview(reviewDto.getReservationId(), reviewDto.getUserId(), reviewDto.getText());
    }

    // 가게 예약 ID로 리뷰 조회하기 (리뷰 작성자 본인 확인용)
    @GetMapping("/reservation/{reservationId}")
    public List<Review> getReviewsByReservation(@PathVariable Long reservationId) {
        return reviewService.getReviewsByReservation(reservationId);
    }

    // 해당 가게의 모든 리뷰 조회하기 (가게아이디로)
    @GetMapping("/list/{storeId}")
    public ResponseEntity<Object> getReviewsByStoreId(@PathVariable Long storeId) {
        List<Review> reviews = reviewService.getReviewsByStore(storeId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(404).body("해당 가게에 대한 리뷰가 없습니다.");
        }
        return ResponseEntity.ok(reviews);
    }


}