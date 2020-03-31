package com.ef.newmis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 程序启动入口程序
 *
 * @author dirxu
 * @date 2020-02-13
 */

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.ef.newmis.mapper")
public class NewmisApplication {

    @PostConstruct
    void started() {

        // 注释 TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));

      }
    public static void main(String[] args) {
        SpringApplication.run(NewmisApplication.class, args);
    }

}



