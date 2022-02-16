package com.qiaoyn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiaoyanan
 * @description <p>用户信息</p>
 * @date 2022-02-16 11:37
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_info")
public class UserInfo {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String userName;
}
