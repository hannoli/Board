package com.example.jwtsecurity.domain.repository;

import com.example.jwtsecurity.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByKeyName(String memberName);
    void deleteByKeyName(String memberName);
}
