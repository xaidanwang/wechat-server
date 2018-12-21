package com.github.aidan.adsl.server.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
public class ADSL {

    @Value("${adsl.host}")
    private String host;
    @Value("${adsl.port}")
    private int port;
    @Value("${adsl.pwd}")
    private String pwd;
    @Value("${adsl.user}")
    private String user;

    public ADSL() {
    }
}
