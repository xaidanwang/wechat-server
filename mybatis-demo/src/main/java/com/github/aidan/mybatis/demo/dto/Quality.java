package com.github.aidan.mybatis.demo.dto;

import lombok.Data;

@Data
public class Quality {

    private String mode;

    private double blur;

    private double occlusion;

    private double completeness;

    private double yaw;

    private double pitch;

    private double roll;


}
