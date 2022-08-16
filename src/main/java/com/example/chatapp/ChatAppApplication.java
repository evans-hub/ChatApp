package com.example.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ChatAppApplication {

    public static void main(String[] args) {
        int port=8080;
        SpringApplication.run(ChatAppApplication.class, args);
        System.out.println("Project started in port :"+port);
    }

}
