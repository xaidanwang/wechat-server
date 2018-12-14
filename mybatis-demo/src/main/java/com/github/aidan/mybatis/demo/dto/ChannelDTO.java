package com.github.aidan.mybatis.demo.dto;

import lombok.Data;

@Data
public class ChannelDTO extends Channel {

    private String channelName;


    // If this property is inherited from the parent class, the value cannot be obtained.
   // private String channeltype;

}
