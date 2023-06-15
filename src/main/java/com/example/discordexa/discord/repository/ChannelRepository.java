package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Integer> {

    @Modifying
    @Query(value = "DELETE FROM is_allowed_in WHERE usr_id = :id", nativeQuery = true)
    void deleteUserFromChannels(@Param("id") Long id);


    @Query(value = "SELECT u FROM Channel u JOIN FETCH u.members where u.id = :id")
    Optional<Channel> getChannelByIAndMembers (@Param("id") Long id);


    List<Channel> findAllByMembers_Id(Long userId);
    
}

