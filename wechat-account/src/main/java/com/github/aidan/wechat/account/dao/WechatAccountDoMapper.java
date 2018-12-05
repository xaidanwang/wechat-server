package com.github.aidan.wechat.account.dao;

import com.github.aidan.wechat.account.entity.WechatAccountDo;

import com.github.aidan.wechat.account.vo.AccountStock;
import com.github.aidan.wechat.account.vo.AccountVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    List<AccountVo> selectByStatus(@Param(value = "status") Integer status);

    int deleteAccount(@Param(value = "status") Integer status,@Param(value = "username") String username);

    List<AccountVo> getAccountList(@Param(value = "id") Long id);

    @MapKey("status")
    Map<Integer,AccountStock> getAccountStock();

    int getAccountTotal();

}