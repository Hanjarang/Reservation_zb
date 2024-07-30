package com.example.reservation.repository;

import com.example.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// User 엔티티에 대한 CRUD 작업을 수행하는 저장소 인터페이스
public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 이름으로 사용자를 찾는 메서
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}

