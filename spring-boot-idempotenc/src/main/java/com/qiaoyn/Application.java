package com.qiaoyn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiaoyanan
 * @description <p>启动类</p>
 * @date 2022-02-09 15:54
 **/
@SpringBootApplication
@MapperScan("com.qiaoyn.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
