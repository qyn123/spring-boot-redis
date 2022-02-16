package com.qiaoyn.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author qiaoyanan
 * @description <p>订单id</p>
 * @date 2022-02-16 11:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "order_info")
public class OrderInfo {

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;
    private Integer userId;
    private String orderName;
}
