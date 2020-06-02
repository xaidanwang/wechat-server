package com.github.aidan.java8.test.juc;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

/**
 * @author wang yi fei
 * @date 2019/5/2 16:10
 */
public class BeanUtilsTest {

	public static void main(String[] args) {
/*		InfrastructureDo roomInfrastructureDo = new InfrastructureDo();
		roomInfrastructureDo.setNextCount(0);
		roomInfrastructureDo.setParentUuid("11111111111111");
		roomInfrastructureDo.setInfrastructureType("house");
		roomInfrastructureDo.setInfrastructureUuid("2132142131");
		roomInfrastructureDo.setAddress("");
		roomInfrastructureDo.setInfrastructureName("02");
		roomInfrastructureDo.setExtInfo(new RoomExtInfo("12321","dsfas",100.00,"02"));

		System.out.println(roomInfrastructureDo);
		InfrastructureRequest request = new InfrastructureRequest();
		BeanUtils.copyProperties(roomInfrastructureDo,request);

		System.out.println(request);

		final JSONObject o = new JSONObject();
		o.put("1","2");
		o.put("2","10");
		System.out.println(o.toJSONString());*/

		for (int i=0;i<10;i++){

			Integer a = 0;
			for (int j =0;j<3;j++){
				System.out.println(a);
				a++;
			}
		}
	}
}
