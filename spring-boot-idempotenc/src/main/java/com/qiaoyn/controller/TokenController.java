package com.qiaoyn.controller;

import com.qiaoyn.config.TokenUtilService;
import com.qiaoyn.entity.AccountInfo;
import com.qiaoyn.entity.OrderInfo;
import com.qiaoyn.entity.UserInfo;
import com.qiaoyn.mapper.AccountInfoMapper;
import com.qiaoyn.mapper.OrderInfoMapper;
import com.qiaoyn.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author qiaoyanan
 * @description <p>验证幂等性</p>
 * @date 2022-02-09 16:09
 **/
@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenUtilService tokenService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 获取 Token 接口
     *
     * @return Token 串
     */
    @GetMapping("/token")
    public String getToken(@RequestParam Integer userId) {
        // 获取用户信息（这里使用模拟数据）
        // 注：这里存储该内容只是举例，其作用为辅助验证，使其验证逻辑更安全，如这里存储用户信息，其目的为:
        // - 1)、使用"token"验证 Redis 中是否存在对应的 Key
        // - 2)、使用"用户信息"验证 Redis 的 Value 是否匹配。
        // 获取 Token 字符串，并返回
        return tokenService.generateToken(String.valueOf(userId));
    }

    /**
     * 接口幂等性测试接口
     *
     * @param token 幂等 Token 串
     * @return 执行结果
     */
    @GetMapping("/test")
    @Transactional
    public String test(@RequestHeader("token") String token,
                       @RequestParam(value = "userId") Integer userId,
                       @RequestParam(value = "accountId") Integer accountId,
                       @RequestParam(value = "price") BigDecimal price,
                       @RequestParam(value = "orderName") String orderName) {
        // 获取用户信息
        UserInfo userInfo =  Optional.ofNullable(userInfoMapper.selectById(userId))
                .orElseThrow(()-> new ExpressionException("用户不存在"));

        // 根据 Token 和与用户相关的信息到 Redis 验证是否存在对应的信息
        boolean flag = tokenService.validToken(token, String.valueOf(userInfo.getUserId()));
        if(flag){

            //订单入库
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUserId(userId).setOrderName(orderName);
            orderInfoMapper.insert(orderInfo);
            //获取账户信息
            AccountInfo accountInfo = Optional.ofNullable(accountInfoMapper.selectById(accountId))
                    .orElseThrow(() -> new ExpressionException("账户不存在"));
            accountInfo.setAccountMoney(accountInfo.getAccountMoney().subtract(price));
            //更新账户金额信息
            accountInfoMapper.updateById(accountInfo);
            return "购买成功";
        }
        // 根据验证结果响应不同信息
        return "请不要重复购买";
    }

}
