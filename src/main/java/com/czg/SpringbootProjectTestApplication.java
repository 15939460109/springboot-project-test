package com.czg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.czg.mapper")
// 打开对定时任务的使用
@EnableScheduling
public class SpringbootProjectTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProjectTestApplication.class, args);
    }

}
