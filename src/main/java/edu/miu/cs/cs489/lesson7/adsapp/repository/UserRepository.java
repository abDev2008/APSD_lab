package edu.miu.cs.cs489.lesson7.adsapp.repository;

import edu.miu.cs.cs489.lesson7.adsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}