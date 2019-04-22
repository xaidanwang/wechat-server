package com.github.aidan.gar.demo.eureka.bean;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author zhangweizheng
 * @TIME 2019-03-01 09:24
 **/
@Data
public class ApplicationsInfo {
    private String versions__delta;
    private String apps__hashcode;
    private List<ApplicationInfo> application;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationsInfo)) return false;
        if (!super.equals(o)) return false;

        ApplicationsInfo that = (ApplicationsInfo) o;

        return getApplication().equals(that.getApplication());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getApplication().hashCode();
        return result;
    }
}