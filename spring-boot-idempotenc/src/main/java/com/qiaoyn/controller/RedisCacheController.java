package com.qiaoyn.controller;

import com.qiaoyn.entity.UserInfo;
import com.qiaoyn.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author qiaoyanan
 * @description <p></p>
 * @date 2022-02-19 16:08
 **/
@RestController
@RequestMapping("/get")
public class RedisCacheController {

    private static final String REDIS_KEY = "users";
    private static final int EXPIRE_TIME = 1;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/user")
    public Object getUsers(Integer userId){
        UserInfo userInfo;
        userInfo = (UserInfo) redisTemplate.opsForValue().get(REDIS_KEY);
        if(Objects.isNull(userInfo)){
            userInfo = userInfoMapper.selectById(userId);
            redisTemplate.opsForValue().set(REDIS_KEY,userInfo,EXPIRE_TIME, TimeUnit.MINUTES);
        }
        return userInfo;
    }

}
