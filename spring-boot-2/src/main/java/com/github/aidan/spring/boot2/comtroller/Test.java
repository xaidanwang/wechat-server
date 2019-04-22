package com.github.aidan.spring.boot2.comtroller;

import com.gato.cloud.sppc.common.response.CommonResult;
import com.gato.cloud.sppc.common.response.ConstantEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wang yi fei
 * @date 2019/3/25 13:16
 */
@RestController
public class Test {


    @RequestMapping(value = "/test/get",method = RequestMethod.GET)
    public CommonResult<String> getTest(){

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS,"这里是 spring-boot-2 服务");
    }


}
