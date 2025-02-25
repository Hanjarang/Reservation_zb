package com.example.reservation.repository;

import com.example.reservation.model.Review;
import com.example.reservation.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStoreId(Long storeId);
    List<Review> findByReservationId(Long reservationId);
    List<Review> findByStoreIdIn(List<Long> storeIds);

}