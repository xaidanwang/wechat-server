package com.github.aidan.gar.demo.eureka.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Description TODO
 * @Author zhangweizheng
 * @TIME 2019-03-01 09:11
 **/
@Data
public class InstanceInfo {
    private String instanceId;
    private String hostName;
    private String app;
    private String ipAddr;
    private String status;
    private String overriddenStatus;
    private JSONObject port;  //{"$":15000,"@enabled":"true"}
    private JSONObject securePort ; //{"$":433,"@enabled":"false"}
    private Integer countryId;
    private JSONObject dataCenterInfo; //{"@class":"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo","name":"MyOwn"}
    private LeaseInfo leaseInfo;
    private JSONObject metadata; //{"management.port":"15000"}
    private String homePageUrl;
    private String statusPageUrl;
    private String healthCheckUrl;
    private String vipAddress;
    private String secureVipAddress;
    private String isCoordinatingDiscoveryServer;
    private String lastUpdatedTimestamp;
    private String lastDirtyTimestamp;
    private String actionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstanceInfo)) return false;
        if (!super.equals(o)) return false;

        InstanceInfo that = (InstanceInfo) o;

        if (!getInstanceId().equals(that.getInstanceId())) return false;
        if (!getHostName().equals(that.getHostName())) return false;
        if (!getApp().equals(that.getApp())) return false;
        if (!getIpAddr().equals(that.getIpAddr())) return false;
        if (!getStatus().equals(that.getStatus())) return false;
        if (!getOverriddenStatus().equals(that.getOverriddenStatus())) return false;
        if (!getPort().equals(that.getPort())) return false;
        if (!getSecurePort().equals(that.getSecurePort())) return false;
        if (!getCountryId().equals(that.getCountryId())) return false;
        if (!getDataCenterInfo().equals(that.getDataCenterInfo())) return false;
        if (!getLeaseInfo().equals(that.getLeaseInfo())) return false;
        if (!getMetadata().equals(that.getMetadata())) return false;
        if (!getHomePageUrl().equals(that.getHomePageUrl())) return false;
        if (!getStatusPageUrl().equals(that.getStatusPageUrl())) return false;
        if (!getHealthCheckUrl().equals(that.getHealthCheckUrl())) return false;
        if (!getVipAddress().equals(that.getVipAddress())) return false;
        if (!getSecureVipAddress().equals(that.getSecureVipAddress())) return false;
        if (!getIsCoordinatingDiscoveryServer().equals(that.getIsCoordinatingDiscoveryServer())) return false;
        if (!getLastUpdatedTimestamp().equals(that.getLastUpdatedTimestamp())) return false;
        if (!getLastDirtyTimestamp().equals(that.getLastDirtyTimestamp())) return false;
        return getActionType().equals(that.getActionType());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getInstanceId().hashCode();
        result = 31 * result + getHostName().hashCode();
        result = 31 * result + getApp().hashCode();
        result = 31 * result + getIpAddr().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getOverriddenStatus().hashCode();
        result = 31 * result + getPort().hashCode();
        result = 31 * result + getSecurePort().hashCode();
        result = 31 * result + getCountryId().hashCode();
        result = 31 * result + getDataCenterInfo().hashCode();
        result = 31 * result + getLeaseInfo().hashCode();
        result = 31 * result + getMetadata().hashCode();
        result = 31 * result + getHomePageUrl().hashCode();
        result = 31 * result + getStatusPageUrl().hashCode();
        result = 31 * result + getHealthCheckUrl().hashCode();
        result = 31 * result + getVipAddress().hashCode();
        result = 31 * result + getSecureVipAddress().hashCode();
        result = 31 * result + getIsCoordinatingDiscoveryServer().hashCode();
        result = 31 * result + getLastUpdatedTimestamp().hashCode();
        result = 31 * result + getLastDirtyTimestamp().hashCode();
        result = 31 * result + getActionType().hashCode();
        return result;
    }
}
