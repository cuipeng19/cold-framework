package com.cold.framework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author cuipeng
 * @since 2018/12/4 11:21
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.cold.framework")
@ComponentScan(basePackages = "com.cold.framework")
@MapperScan(basePackages = "com.cold.framework.dao.mapper")
public class ColdApplication {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ColdApplication.class,args);
    }

    @Bean
    public RestTemplate build() {
        restTemplateBuilder.setConnectTimeout(10000);
        restTemplateBuilder.setReadTimeout(10000);
        return restTemplateBuilder.build();
    }
}
