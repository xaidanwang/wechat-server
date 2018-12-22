package com.github.aidan.adsl.server.dao;

import com.github.aidan.adsl.server.entity.AdslAccountDo;
import org.apache.ibatis.annotations.Param;

public interface AdslAccountDoMapper {
    int deleteByPrimaryKey(Long accountid);

    int insert(AdslAccountDo record);

    int insertSelective(AdslAccountDo record);

    AdslAccountDo selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(AdslAccountDo record);

    int updateByPrimaryKey(AdslAccountDo record);

    AdslAccountDo getOneAdslAccountDo();

    int deleteAdslAccount(@Param(value = "host") String host);

    int deleteAll();

    int updateWechatAccount(@Param(value = "host")String host,@Param(value = "port") Integer port,@Param(value = "useable")  boolean useable);
}