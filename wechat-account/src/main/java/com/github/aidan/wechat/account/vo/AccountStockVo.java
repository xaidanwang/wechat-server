package com.github.aidan.wechat.account.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="AccountStockVo",description="库存账号信息")
public class AccountStockVo {

    private Integer totalCount;

    private Integer useableCount;

    private Integer forbiddenCount;

    private Integer releaseFailCount;

}
