package com.github.aidan.adsl.server.service;

import com.github.aidan.adsl.server.bean.ADSL;
import org.springframework.web.multipart.MultipartFile;

public interface AdslService {

    ADSL getAdslAccount();
    String deleteAdslAccount(String host);

    String deleteAll();

    String uploadFile(boolean useable,MultipartFile file);
    String updateWechatAccount(String host,Integer port,boolean useable);
}
