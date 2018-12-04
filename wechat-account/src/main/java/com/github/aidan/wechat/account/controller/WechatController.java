package com.github.aidan.wechat.account.controller;

import com.github.aidan.wechat.account.service.WechatService;
import com.github.aidan.wechat.account.util.EmptyUtil;
import com.github.aidan.wechat.account.util.FileUtil;
import com.github.aidan.wechat.account.util.StreamUtil;
import com.github.aidan.wechat.account.vo.AccountVo;
import com.sun.deploy.net.URLEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Api(value = "WechatController", tags = {"wechat api"},description = "微信账号接口" )
@RestController
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @ApiOperation(value = "轮询获取账号信息",httpMethod = "GET",notes = "轮询获取账号信息")
    @RequestMapping(value = "/wechat",method = RequestMethod.GET)
    public AccountVo getWechatAccount(){

        return wechatService.getWechatAccount();

    }


    @ApiOperation(value = "上传账号信息",httpMethod = "POST",notes = "上传账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",required = true, dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/wechat/upload",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE },
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String uploadFile(Integer status,MultipartFile file){

        return wechatService.uploadFile(status,file);
    }

    @ApiOperation(value = "更新账号信息",httpMethod = "PUT",notes = "更新账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "账号",name = "uername",required = true,dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",required = true, dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/wechat",method = RequestMethod.PUT)
    public String updateWechatAccount(String uername,Integer status){

        return wechatService.updateWechatAccount(uername,status);

    }

    @ApiOperation(value = "获取指定状态的账号",httpMethod = "GET",notes = "获取指定状态的账号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",required = true, dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/wechat/status",method = RequestMethod.GET)
    public List<AccountVo> getWechatAccountByStatus(Integer status){
        return wechatService.getWechatAccountByStatus(status);
    }

    @ApiOperation(value = "下载指定状态的所有账号",httpMethod = "GET",notes = "下载指定状态的所有账号")
    @RequestMapping(value = "/wechat/download",method = RequestMethod.GET)
    public void download(Integer status, HttpServletRequest request, HttpServletResponse response){

        File file = wechatService.download(status,request,response);
        System.out.println(file.getAbsolutePath());
        String fielName = FileUtil.getFileName(status);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (EmptyUtil.isNotEmpty(file)){
            try {
              //  fielName = new String(fielName.getBytes(), "ISO-8859-1");
                fielName = URLEncoder.encode(fielName,"UTF-8");
                inputStream = new FileInputStream(file);
                outputStream = response.getOutputStream();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/txt;charset=UTF-8");
                response.addHeader("Content-Disposition", "attachment;filename=" + fielName);
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                StreamUtil.closeCloseable(inputStream);
                StreamUtil.closeCloseable(outputStream);
            }
        }

        if (file.exists()){
            file.delete();
        }

    }


    @ApiOperation(value = "删除账号",httpMethod = "DELETE",notes = "删除账号")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "账号",name = "uername",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "状态[1 正常，2 封号，3 解封，4 待定]",name = "status",dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/wechat",method = RequestMethod.DELETE)
    public String deleteAccount(Integer status,String username){

        return wechatService.deleteAccount(status,username);

    }



}
