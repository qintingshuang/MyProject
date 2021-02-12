package com.qintingshuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qintingshuang
 * @create 2021-02-10 15:40
 * @description 启动类
 **/

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class QintingshuangServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QintingshuangServiceApplication.class, args);
    }

}
