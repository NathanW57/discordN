package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Integer> {

    @Modifying
    @Query(value = "DELETE FROM is_member_of WHERE usr_id = :id", nativeQuery = true)
    void deleteUserFromGroups(@Param("id") Long id);


    @Modifying
    @Query(value= "DELETE FROM user_group WHERE gro_id = :id", nativeQuery = true)
    void deleteGroup(@Param("id") Long id);


    @Query(value = "DELETE FROM is_member_of WHERE gro_id = :id", nativeQuery = true)
    void deleteGroupMembers(@Param("id") Long id);
}


