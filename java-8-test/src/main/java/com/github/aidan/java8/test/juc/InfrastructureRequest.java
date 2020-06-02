package com.github.aidan.java8.test.juc;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ClassName InfrastructureRequest
 * @Version: 1.0
 * @Description TODO
 * @Author lu.yi
 * @create: 2019-04-08 10:26
 **/
@Data
public class InfrastructureRequest {

    private String infrastructureUuid;           //基建uuid
    private String infrastructureName;           //基建名称
    private String parentUuid;                   //父基建uuid
    private String infrastructureUri;            //基建uri
    private String infrastructureType;           //基建类型
    private String address;                      //地址
    private String summary;                      //简介
    private Integer nextCount;                   //下一级数量
    private Object extInfo;       //扩展信息
    private String remarks;                      //备注
}
