package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel,Integer> {

    @Modifying
    @Query(value = "DELETE FROM is_allowed_in WHERE usr_id = :id", nativeQuery = true)
    void deleteUserFromChannels(@Param("id") Long id);
}
