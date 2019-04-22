package com.github.aidan.gar.demo.eureka.bean;

import lombok.Data;

/**
 * @Description TODO
 * @Author zhangweizheng
 * @TIME 2019-03-01 09:10
 **/
@Data
public class ServiceInstanceDTO {

    private ApplicationsInfo applications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceInstanceDTO)) return false;
        if (!super.equals(o)) return false;

        ServiceInstanceDTO that = (ServiceInstanceDTO) o;

        return getApplications().equals(that.getApplications());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getApplications().hashCode();
        return result;
    }
}
