package com.github.aidan.wechat.account.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AccountVo",description="微信账号信息")
public class AccountVo {

    private String username;

    private String password;

    private String token;

    private String data;
}
