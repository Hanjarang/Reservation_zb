package com.example.reservation.dto;

public class ReservationCheckRequest {
    private Long storeId;
    private Long userId;

    // Getters and Setters
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
