package com.czg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.czg.mapper")
// 打开对定时任务的使用
@EnableScheduling
// 增加异步任务的开关
@EnableAsync
public class SpringbootProjectTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProjectTestApplication.class, args);
    }

}
