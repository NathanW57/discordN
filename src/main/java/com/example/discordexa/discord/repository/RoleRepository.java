package com.example.discordexa.discord.repository;

import com.example.discordexa.discord.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
