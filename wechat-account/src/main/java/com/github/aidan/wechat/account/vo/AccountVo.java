package com.github.aidan.wechat.account.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AccountVo",description="微信账号信息")
public class AccountVo {

    private Long id;

    private String username;

    private String password;

    private String token;

    private String data;

    public String printVo(){
        return username+"----"+password+"----"+token+"----"+data+"\r\n";
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", data='" + data + '\'' +
                '}';
    }


}
