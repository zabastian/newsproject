package com.example.newsproject.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.newsproject.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
//    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
//    Optional<User> findByUser(DecodedJWT user);

}
