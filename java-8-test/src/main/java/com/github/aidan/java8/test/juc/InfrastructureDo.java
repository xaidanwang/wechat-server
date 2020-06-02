package com.github.aidan.java8.test.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wang yi fei
 * @date 2019/5/2 10:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfrastructureDo<T> {

	// 基建uuid
	private String infrastructureUuid;
	// 基建名称
	private String infrastructureName;
	/**
	 * 父基建uuid
 	 */
	private String parentUuid;
	// 基建uri
	private String infrastructureUri;
	// 基建类型
	private String infrastructureType;
	// 地址
	private String address;
	// 简介
	private String summary;
	/**
	 * 下一级数量
	 */
	private Integer nextCount;
	/**
	 * 扩展信息
	 */
	private T extInfo;
	// 备注
	private String remarks;

}
