package com.example.reservation.controller;

import com.example.reservation.dto.ReservationCheckRequest;
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

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping("/check")
    public ResponseEntity<Object> checkReservation(@RequestBody ReservationCheckRequest request) {
        Optional<Reservation> reservation = reservationService.getReservationByStoreIdAndUserId(request.getStoreId(), request.getUserId());
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            return ResponseEntity.status(404).body("예약자 정보가 없습니다.");
        }
    }

    @PostMapping("/arrival")
    public ResponseEntity<String> confirmArrival(@RequestBody Map<String, Long> request) {
        Long reservationId = request.get("reservationId");
        if (reservationId == null) {
            return ResponseEntity.status(400).body("예약 ID가 제공되지 않았습니다.");
        }

        Reservation reservation = reservationService.findById(reservationId);
        if (reservation == null) {
            return ResponseEntity.status(404).body("예약을 찾을 수 없습니다.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservationTime = reservation.getReservationTime();

        if (reservationTime.isBefore(now.plusMinutes(10)) && reservationTime.isAfter(now.minusMinutes(10))) {
            reservation.setArrivalConfirmed(true);  // 도착 확인 상태 설정
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("도착이 확인되었습니다.");
        } else {
            return ResponseEntity.status(400).body("예약 확인 시간에 도착하지 않았습니다.");
        }
    }
}
