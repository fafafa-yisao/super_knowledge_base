package com.example.searchhot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SearchHotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchHotApplication.class, args);
    }

}
