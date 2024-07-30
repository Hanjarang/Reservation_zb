package com.example.reservation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime reservationTime;
    private boolean arrivalConfirmed;  // 도착 확인 상태

    private boolean completed;         // 예약 완료 여부를 나타내는 필드

    // 예약이 완료되었는지 여부를 반환하는 메소드
    public boolean isCompleted() {
        return completed;
    }
}
