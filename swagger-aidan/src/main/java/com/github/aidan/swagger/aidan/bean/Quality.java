package com.github.aidan.swagger.aidan.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="比对质量")
public class Quality {

    @ApiModelProperty(value = "图片模糊度")
    private double blurDegree;

    @ApiModelProperty(value = "人脸遮挡度")
    private double occlusionDegree;

    @ApiModelProperty(value = "人脸完整度")
    private double integrityDegree;

    @ApiModelProperty(value = "水平转动角")
    private double horizontalAngle;

    @ApiModelProperty(value = "俯仰角")
    private double pitchAngle;

    @ApiModelProperty(value = "倾斜角")
    private double bankAngle;


}
