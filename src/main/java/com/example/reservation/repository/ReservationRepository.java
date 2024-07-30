package com.example.reservation.repository;

import com.example.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByStoreId(Long storeId);
    Optional<Reservation> findByStoreIdAndUserId(Long storeId, Long userId);
}
