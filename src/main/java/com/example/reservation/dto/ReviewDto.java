package com.example.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long id;
    private Long storeId;
    private String text;
    private Long userId;
    private Long reservationId;
}