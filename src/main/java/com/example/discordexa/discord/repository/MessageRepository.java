package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> getAllBySenderId(Integer id);

    @Query("SELECT m FROM Message m WHERE m.channel.id = ?1")
    List<Message> getAllByChannelId(Integer id);

    void deleteAllBySenderId(Long id);


}
