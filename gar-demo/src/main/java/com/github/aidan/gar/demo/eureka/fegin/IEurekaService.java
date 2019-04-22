package com.github.aidan.gar.demo.eureka.fegin;

import com.github.aidan.gar.demo.eureka.bean.InstanceInfo;
import com.github.aidan.gar.demo.eureka.bean.ServiceInstanceDTO;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 远程调用http
 * @Author zhangweizheng
 * @TIME 2018-09-10 17:03
 **/
@FeignClient(name = "IEurekaService",url = "http://{eureka.host}/${eureka.port}")
@Headers({"Content-Type: application/json","Accept: application/json"})
@RequestMapping(value = "/eureka/apps")
public interface IEurekaService {


    /**
     * 查询所有eureka实例
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    ServiceInstanceDTO queryEurekaApps();

    @RequestMapping(value="/{appID}",method = RequestMethod.POST)
    String registerApplicationInstance(@PathVariable(value = "appID")String appID,@RequestBody InstanceInfo instanceInfo);

}
