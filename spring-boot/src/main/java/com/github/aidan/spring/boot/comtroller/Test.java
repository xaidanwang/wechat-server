package com.github.aidan.spring.boot.comtroller;

import com.gato.cloud.sppc.common.response.CommonResult;
import com.gato.cloud.sppc.common.response.ConstantEnum;
import com.github.aidan.spring.boot.entity.Student;
import com.github.aidan.spring.boot.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang yi fei
 * @date 2019/3/25 13:16
 */
@RestController
public class Test {


    @RequestMapping(value = "/test/get",method = RequestMethod.GET)
    public CommonResult<User> getTest(HttpServletRequest request, HttpServletResponse response){

        Student student1 = new Student("100","101");
        Student student2 = new Student("102","103");
        Map<String,Student> map = new HashMap<>(2);
        map.put("student1",student1);
        map.put("student2",student2);

        User user = new User("王亦非",25,map);
        System.out.println("调用到 spring-boot-1");
        response.setHeader("test","test1");
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS,user);
    }

    @RequestMapping(value = "/test/get",method = RequestMethod.DELETE)
    public CommonResult<User> deleteTest(HttpServletRequest request, HttpServletResponse response){

        Student student1 = new Student("100","101");
        Student student2 = new Student("102","103");
        Map<String,Student> map = new HashMap<>(2);
        map.put("student1",student1);
        map.put("student2",student2);

        User user = new User("王亦非",25,map);
        System.out.println("调用到 spring-boot-1");
        response.setHeader("test","test1");
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS,user);
    }

}
