package com.zhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * SpringBoot工程启动类
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "com.zhy.mapper")
public class ZhyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhyApplication.class);
    }
}
