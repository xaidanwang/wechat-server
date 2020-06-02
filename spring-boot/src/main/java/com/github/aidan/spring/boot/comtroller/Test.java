package com.github.aidan.spring.boot.comtroller;

import com.gato.cloud.sppc.common.response.CommonResult;
import com.gato.cloud.sppc.common.response.ConstantEnum;
import com.github.aidan.spring.boot.entity.Student;
import com.github.aidan.spring.boot.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

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

        User user = new User("wang",25,map);
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

    @RequestMapping(value = "/file/post",method = RequestMethod.POST)
        public CommonResult<String> postTest(HttpServletRequest request,@RequestBody MultipartFile multipartFile) throws IOException {
        Enumeration<String> headerKey =  request.getHeaderNames();
        while (headerKey.hasMoreElements()){
            String key = headerKey.nextElement();
            System.out.println(key+": "+request.getHeader(key));
        }
        Enumeration<String> param = request.getParameterNames();
        System.out.println("=================Param==========================");
        while (param.hasMoreElements()){
            String key = param.nextElement();
            System.out.println(key+": "+request.getParameter(key));
        }
        System.out.println("文件名称为："+ multipartFile.getOriginalFilename());
        System.out.println("文件大小：" + multipartFile.getSize());
        File fileadd = new File("D:\\usr\\"+ multipartFile.getOriginalFilename());
      //  File fileadd = new File(multipartFile.getOriginalFilename());
        if (fileadd.exists()){
            fileadd.delete();
        }
        multipartFile.transferTo(fileadd);
        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS,"文件上传成功");
    }

}
