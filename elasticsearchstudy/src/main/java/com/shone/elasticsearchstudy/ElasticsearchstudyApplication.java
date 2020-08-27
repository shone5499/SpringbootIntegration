package com.shone.elasticsearchstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.shone.elasticsearchstudy.mapper")
@SpringBootApplication
public class ElasticsearchstudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchstudyApplication.class, args);
    }

}
