package com.github.aidan.swagger.aidan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="DispatchingTaskRequest",description="布控任务请求body")
public class DispatchingTaskRequest {
    @ApiModelProperty(value = "任务uuid")
    private String taskuuid;

    @ApiModelProperty(value = "任务名称")
    private String taskname;

    @ApiModelProperty(value = "操作人uuid")
    private String useruuid;

    @ApiModelProperty(value = "操作人姓名")
    private String username;

    @ApiModelProperty(value = "布控原因")
    private String reason;
    @ApiModelProperty(value = "阈值")
    private Double threshold;

    @ApiModelProperty(value = "是否开启任务")
    private Boolean enabled;

    @ApiModelProperty(value = "是否弹窗")
    private Boolean ispopup;

    @ApiModelProperty(value = "比对质量",dataType = "Quality")
    private String quality;

}
