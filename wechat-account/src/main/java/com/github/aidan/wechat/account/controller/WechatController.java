package com.github.aidan.wechat.account.controller;

import com.github.aidan.wechat.account.service.WechatService;
import com.github.aidan.wechat.account.vo.AccountVo;
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


@Api(value = "WechatController", tags = {"wechat api"},description = "微信账号接口" )
@RestController
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @ApiOperation(value = "获取账号信息",httpMethod = "GET",notes = "获取账号信息")
    @RequestMapping(value = "/wechat",method = RequestMethod.GET)
    public AccountVo getWechatAccount(){

        return wechatService.getWechatAccount();

    }


    @ApiOperation(value = "上传账号信息",httpMethod = "POST",notes = "上传账号信息")
    @RequestMapping(value = "/wechat/upload",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String uploadFile(MultipartFile file){

        return wechatService.uploadFile(file);
    }

    @ApiOperation(value = "更新账号信息",httpMethod = "PUT",notes = "更新账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "账号",name = "uername",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",required = true, dataType = "string",paramType = "query")
    })
    @RequestMapping(value = "/wechat",method = RequestMethod.PUT)
    public String updateWechatAccount(String uername,Integer status){

        return wechatService.updateWechatAccount(uername,status);

    }

    @ApiOperation(value = "获取指定状态的账号",httpMethod = "GET",notes = "获取指定状态的账号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",required = true, dataType = "string",paramType = "query")
    })
    @RequestMapping(value = "/wechat/status",method = RequestMethod.GET)
    public AccountVo getWechatAccountByStatus(Integer status){
        return wechatService.getWechatAccount();
    }


}
