package com.example.discordexa.discord.mapper;

import com.example.discordexa.discord.DTO.ChannelCreateDTO;
import com.example.discordexa.discord.DTO.ChannelGetDTO;
import com.example.discordexa.discord.DTO.ChannelGetFinestDTO;
import com.example.discordexa.discord.Enum.EVisibility;
import com.example.discordexa.discord.bean.Channel;

public class ChannelMapper {

    public static Channel toEntity(ChannelCreateDTO channelCreateDTO) {
        Channel channel = new Channel();
        channel.setName(channelCreateDTO.getName());
        channel.setVisibility(EVisibility.valueOf(channelCreateDTO.getVisibility()));
        return channel;
    }

    public static ChannelGetDTO toGetDto(Channel channel) {
        ChannelGetDTO channelGetDTO = new ChannelGetDTO();
        channelGetDTO.setId(channel.getId());
        channelGetDTO.setName(channel.getName());
        channelGetDTO.setVisibility(channel.getVisibility().toString());
        return channelGetDTO;
    }

    public static ChannelGetFinestDTO toGetFinestDto(Channel channel) {
        ChannelGetFinestDTO channelGetFinestDTO = new ChannelGetFinestDTO();
        channelGetFinestDTO.setId(channel.getId());
        channelGetFinestDTO.setName(channel.getName());
        channelGetFinestDTO.setVisibility(channel.getVisibility().toString());
        return channelGetFinestDTO;
    }

}
