package com.example.discordexa.discord.mapper;

import com.example.discordexa.discord.DTO.GroupCreateDTO;
import com.example.discordexa.discord.DTO.GroupGetDTO;
import com.example.discordexa.discord.DTO.GroupGetFinestDTO;
import com.example.discordexa.discord.bean.Group;

public class GroupMapper {


    public static Group toEntity(GroupCreateDTO groupCreateDTO) {
        Group group = new Group();
        group.setName(groupCreateDTO.getName());
        return group;
    }

    public static GroupGetDTO toGetDto(Group channel) {
        GroupGetDTO groupGetDTO = new GroupGetDTO();
        groupGetDTO.setId(channel.getId());
        groupGetDTO.setName(channel.getName());
        return groupGetDTO;
    }

    public static GroupGetFinestDTO toGetFinestDto(Group group) {
        GroupGetFinestDTO groupGetFinestDTO = new GroupGetFinestDTO();
        groupGetFinestDTO.setId(group.getId());
        groupGetFinestDTO.setName(group.getName());
        groupGetFinestDTO.setMembers(group.getMembers().stream().map(UserMapper::toGetDto).toList());
        return groupGetFinestDTO;
    }

}
