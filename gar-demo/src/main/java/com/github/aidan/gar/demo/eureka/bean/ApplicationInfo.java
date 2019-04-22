package com.github.aidan.gar.demo.eureka.bean;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author zhangweizheng
 * @TIME 2019-03-01 09:23
 **/
@Data
public class ApplicationInfo {
    private String name;
    private List<InstanceInfo> instance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationInfo)) return false;
        if (!super.equals(o)) return false;

        ApplicationInfo that = (ApplicationInfo) o;

        if (!getName().equals(that.getName())) return false;
        return getInstance().equals(that.getInstance());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getInstance().hashCode();
        return result;
    }
}
