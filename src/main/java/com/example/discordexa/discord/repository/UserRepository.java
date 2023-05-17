package com.example.discordexa.discord.repository;


import com.example.discordexa.discord.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT u FROM User u JOIN FETCH u.role")
    Optional<User> findByEmail(String email);
}
