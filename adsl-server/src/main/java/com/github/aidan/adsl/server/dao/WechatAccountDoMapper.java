package com.github.aidan.adsl.server.dao;

import com.github.aidan.adsl.server.entity.WechatAccountDo;

public interface WechatAccountDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatAccountDo record);

    int insertSelective(WechatAccountDo record);

    WechatAccountDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatAccountDo record);

    int updateByPrimaryKeyWithBLOBs(WechatAccountDo record);

    int updateByPrimaryKey(WechatAccountDo record);
}