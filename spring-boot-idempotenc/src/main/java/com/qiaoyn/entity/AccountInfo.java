package com.qiaoyn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author qiaoyanan
 * @description <p>账户信息</p>
 * @date 2022-02-16 11:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "account_info")
public class AccountInfo {

    @TableId(value = "account_id", type = IdType.AUTO)
    private Integer accountId;
    private Integer userId;
    private BigDecimal accountMoney;
}
