package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 */

@tk.mybatis.spring.annotation.MapperScan("com.baizhi.dao")
@MapperScan("com.baizhi.dao")
@SpringBootApplication
public class YingxShijtApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxShijtApplication.class, args);
    }

    /*protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YingxShijtApplication.class);
    }*/


}
