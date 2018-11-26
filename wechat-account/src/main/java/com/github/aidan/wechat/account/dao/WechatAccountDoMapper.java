package com.github.aidan.wechat.account.dao;

import com.github.aidan.wechat.account.entity.WechatAccountDo;

import com.github.aidan.wechat.account.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatAccountDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatAccountDo record);

    int insertSelective(WechatAccountDo record);

    WechatAccountDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatAccountDo record);

    int updateByPrimaryKeyWithBLOBs(WechatAccountDo record);

    int updateByPrimaryKey(WechatAccountDo record);

    WechatAccountDo selectByUername(@Param(value = "username") String username);

    int updateByUsername(WechatAccountDo wechatAccountDo);

    AccountVo selectByStatus(@Param(value = "status") Integer status);

}