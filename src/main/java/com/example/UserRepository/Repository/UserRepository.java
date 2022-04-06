package com.example.UserRepository.Repository;

import com.example.UserRepository.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
