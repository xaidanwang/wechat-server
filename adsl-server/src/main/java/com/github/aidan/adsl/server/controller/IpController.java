package com.github.aidan.adsl.server.controller;

import com.github.aidan.adsl.server.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IpController {

    @Autowired
    private IpService ipService;

    @RequestMapping(value = "/ip",method = RequestMethod.GET)
    @ResponseBody
    public String getIp() throws IOException {

        return ipService.getIp();
    }

    @RequestMapping(value = "/refresh",method = RequestMethod.GET)
    @ResponseBody
    public String refresh(){

        String result =null;
        try {
            result =  ipService.refreshIp();
        } catch (IOException e) {
            e.printStackTrace();
            result = "fail";
        }
        return result;
    }

}
