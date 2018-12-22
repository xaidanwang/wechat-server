package com.github.aidan.adsl.server.controller;

import com.github.aidan.adsl.server.bean.ADSL;
import com.github.aidan.adsl.server.service.AdslService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Api(value = "AsdslController", tags = {"adsl api"},description = "vps账号接口" )
@RestController
public class AsdslController {


    @Autowired
    private AdslService adslService;

    @ApiOperation(value = "获取adsl账号信息",httpMethod = "GET",notes = "获取adsl账号信息")
    @RequestMapping(value = "/adsl",method = RequestMethod.GET)
    public ADSL getAdslAccount(){
        return adslService.getAdslAccount();
    }

    @ApiOperation(value = "删除adsl账号信息",httpMethod = "DELETE",notes = "删除adsl账号信息")
    @RequestMapping(value = "/adsl",method = RequestMethod.DELETE)
    public String deleteAdslAccount(String host){
        return adslService.deleteAdslAccount(host);
    }

    @ApiOperation(value = "清空",httpMethod = "DELETE",notes = "清空")
    @RequestMapping(value = "/adsl/all",method = RequestMethod.DELETE)
    public String deleteAll(){
        return adslService.deleteAll();
    }

    @ApiOperation(value = "上传账号信息",httpMethod = "POST",notes = "上传账号信息")
    @RequestMapping(value = "/adsl/upload",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String uploadFile(boolean useable,MultipartFile file){
        return adslService.uploadFile(useable,file);
    }

    @ApiOperation(value = "更新账号",httpMethod = "PUT",notes = "更新账号")
    @RequestMapping(value = "/wechat",method = RequestMethod.PUT)
    public String updateWechatAccount(String host,Integer port,boolean useable){
        System.out.println("");
        return adslService.updateWechatAccount(host,port,useable);
    }
}
