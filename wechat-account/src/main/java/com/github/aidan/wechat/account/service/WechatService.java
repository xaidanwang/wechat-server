package com.github.aidan.wechat.account.service;

import com.github.aidan.wechat.account.vo.AccountVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface WechatService {

    AccountVo getWechatAccount();

    String uploadFile(Integer status,MultipartFile file);

    String updateWechatAccount(String uername,Integer status);

    List<AccountVo> getWechatAccountByStatus(Integer status);

    /**
     * 下载指定状态账号的信息
     * @param status
     * @param request
     * @param response
     * @return
     */
    File download(Integer status, HttpServletRequest request, HttpServletResponse response);

    String deleteAccount(Integer status,String username);
}
