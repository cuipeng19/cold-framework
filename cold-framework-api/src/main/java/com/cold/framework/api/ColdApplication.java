package com.cold.framework.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.time.Duration;

/**
 * @author cuipeng
 * @since 2018/12/4 11:21
 */
@SpringBootApplication
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
        Duration duration = Duration.ofSeconds(10);
        this.restTemplateBuilder.setConnectTimeout(duration);
        this.restTemplateBuilder.setReadTimeout(duration);
        return this.restTemplateBuilder.build();
    }
}
