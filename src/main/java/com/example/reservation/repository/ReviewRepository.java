package com.example.reservation.repository;

import com.example.reservation.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReservationId(Long reservationId); // 예약자가 작성한 리뷰 조회
    List<Review> findByStoreId(Long storeId); // 가게의 모든 리뷰 조회
}
