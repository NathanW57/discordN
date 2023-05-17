package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Notification;
import com.example.discordexa.discord.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
