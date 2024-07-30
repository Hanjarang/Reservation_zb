package com.example.reservation.controller;

import com.example.reservation.dto.ReservationRequest;
import com.example.reservation.model.Reservation;
import com.example.reservation.model.Store;
import com.example.reservation.model.User;
import com.example.reservation.service.ReservationService;
import com.example.reservation.service.StoreService;
import com.example.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserService userService;
    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest) {
        User user = userService.findById(reservationRequest.getUserId());
        if (user == null) {
            return ResponseEntity.status(404).body(null); // 사용자가 존재하지 않는 경우 처리
        }

        Store store = storeService.findById(reservationRequest.getStoreId());
        if (store == null) {
            return ResponseEntity.status(404).body(null); // 가게가 존재하지 않는 경우 처리
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setStore(store);
        reservation.setReservationTime(reservationRequest.getReservationTime());

        Reservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }
}
