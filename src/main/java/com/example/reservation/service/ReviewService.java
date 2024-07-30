package com.example.reservation.service;

import com.example.reservation.model.Reservation;
import com.example.reservation.model.Review;
import com.example.reservation.model.User;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.repository.ReviewRepository;
import com.example.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public void addReview(Long reservationId, Long userId, String text) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 예약과 사용자가 일치하는지 확인
        if (!reservation.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("User is not allowed to review this reservation");
        }

        Review review = new Review();
        review.setReservation(reservation);
        review.setUser(user);
        review.setText(text);

        reviewRepository.save(review);
    }

    public List<Review> getReviewsByReservation(Long reservationId) {
        return reviewRepository.findByReservationId(reservationId);
    }
    public List<Review> getReviewsByStore(Long storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

}
