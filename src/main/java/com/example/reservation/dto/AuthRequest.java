package com.example.reservation.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthRequest {
    private String username;
    private String password;

    // 기본 생성자
    public AuthRequest() {
    }

    // 매개변수가 있는 생성자
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public String toString() {
        return "AuthRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
