package com.prakhar.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakhar.client.entity.PasswordResetToken;

@Repository
public interface PasswordRestTokenRepository extends JpaRepository<PasswordResetToken,Long> {

}
