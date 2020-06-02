package com.github.aidan.java8.test.juc;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wang yi fei
 * @date 2019/5/2 13:37
 */
@Data
@AllArgsConstructor
public class RoomExtInfo {

	@ApiModelProperty(value="房屋类型",name="roomsType")
	private String roomsType;


	@ApiModelProperty(value="房屋用途",name="roomsUse")
	private String roomsUse;

	// 房屋面积
	private Double area;

	// 房号
	private String roomNo;


}
