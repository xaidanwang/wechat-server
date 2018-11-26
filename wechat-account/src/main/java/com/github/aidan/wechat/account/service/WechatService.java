package com.github.aidan.wechat.account.service;

import com.github.aidan.wechat.account.vo.AccountVo;
import org.springframework.web.multipart.MultipartFile;

public interface WechatService {

    AccountVo getWechatAccount();

    String uploadFile(MultipartFile file);

    String updateWechatAccount(String uername,Integer status);

    AccountVo getWechatAccountByStatus(Integer status);
}
