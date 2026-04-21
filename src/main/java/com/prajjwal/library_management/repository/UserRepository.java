package com.prajjwal.library_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prajjwal.library_management.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
