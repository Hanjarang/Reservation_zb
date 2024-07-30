package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long userId;
    private Long reservationId;
    private String text;
}
