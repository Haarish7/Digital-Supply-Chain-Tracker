package com.example.supplychain.repository;

import com.example.supplychain.entity.User;
import com.example.supplychain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login)
    Optional<User> findByEmail(String email);

    // Find all users with a specific role
    List<User> findByRole(Role role);

    // Check if email already exists (used in registration validation)
    boolean existsByEmail(String email);
}
