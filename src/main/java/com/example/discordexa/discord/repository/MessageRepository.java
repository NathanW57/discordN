package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Channel;
import com.example.discordexa.discord.bean.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> getAllBySenderId(Integer id);
}
