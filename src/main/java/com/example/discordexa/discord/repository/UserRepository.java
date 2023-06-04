package com.example.discordexa.discord.repository;


import com.example.discordexa.discord.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT u FROM User u JOIN FETCH u.role where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);


    @Query(value = "SELECT u FROM User u JOIN FETCH u.role where u.id = :id")
    Optional<User> findByIdRole(@Param("id") long id);

    @Query(value = "SELECT u.id FROM User u")
    List<Long> findAllIdsAsLong();

    default Iterable<Integer> findAllIds() {
        return findAllIdsAsLong().stream().map(Long::intValue).collect(Collectors.toList());
    }


}
