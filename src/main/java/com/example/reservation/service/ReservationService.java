package com.example.reservation.service;

import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> getReservationsByStoreId(Long storeId) {
        return reservationRepository.findByStoreId(storeId);
    }
    public Optional<Reservation> getReservationByStoreIdAndUserId(Long storeId, Long userId) {
        return reservationRepository.findByStoreIdAndUserId(storeId, userId);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
