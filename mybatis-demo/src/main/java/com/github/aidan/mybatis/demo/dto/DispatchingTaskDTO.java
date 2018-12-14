package com.github.aidan.mybatis.demo.dto;

import lombok.Data;

import java.util.List;


@Data
public class DispatchingTaskDTO extends BaseTaskInfo {

    private List<ChannelDTO> channelDTOS;

    private List<FaceLibDTO> faceLibDTOS;

// If these  property is inherited from the parent class, the value cannot be obtained.
/*    protected Integer threshold;

    protected Boolean ispopup;

    protected Quality quality;
    protected Boolean alarmed;*/
}
