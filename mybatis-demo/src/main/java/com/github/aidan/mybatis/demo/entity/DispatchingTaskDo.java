package com.github.aidan.mybatis.demo.entity;




import com.github.aidan.mybatis.demo.dto.Quality;

import java.util.Date;

public class DispatchingTaskDo {
    private Long keyid;

    private String taskuuid;

    private String taskname;

    private String useruuid;

    private String username;

    private String reason;

    private Integer threshold;

    private Boolean isglobal;

    private Boolean enabled;

    private Boolean deleted;

    private Boolean ispopup;

    private Boolean alarmed;

    private String alarmlevel;

    private Date createtime;

    private Date updatetime;

    private String remark;

    private String platformuuid;

    private String precode;

    private String prename;

   // private String quality;

    private Quality quality;

    public Long getKeyid() {
        return keyid;
    }

    public void setKeyid(Long keyid) {
        this.keyid = keyid;
    }

    public String getTaskuuid() {
        return taskuuid;
    }

    public void setTaskuuid(String taskuuid) {
        this.taskuuid = taskuuid == null ? null : taskuuid.trim();
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Boolean getIsglobal() {
        return isglobal;
    }

    public void setIsglobal(Boolean isglobal) {
        this.isglobal = isglobal;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getIspopup() {
        return ispopup;
    }

    public void setIspopup(Boolean ispopup) {
        this.ispopup = ispopup;
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel == null ? null : alarmlevel.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPlatformuuid() {
        return platformuuid;
    }

    public void setPlatformuuid(String platformuuid) {
        this.platformuuid = platformuuid == null ? null : platformuuid.trim();
    }

    public String getPrecode() {
        return precode;
    }

    public void setPrecode(String precode) {
        this.precode = precode == null ? null : precode.trim();
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename == null ? null : prename.trim();
    }

 /*   public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }*/

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Boolean getAlarmed() {
        return alarmed;
    }

    public void setAlarmed(Boolean alarmed) {
        this.alarmed = alarmed;
    }
}