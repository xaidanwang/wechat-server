package com.github.aidan.wechat.account.entity;

import java.util.Date;

public class WechatAccountDo {
    private Long id;

    private String username;

    private String password;

    private String token;

    private Date createtime;

    private Date updateTime;

    private Boolean isusable;

    private Integer status;

    private String remark;

    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date midifytime) {
        this.updateTime = midifytime;
    }

    public Boolean getIsusable() {
        return isusable;
    }

    public void setIsusable(Boolean isusable) {
        this.isusable = isusable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}