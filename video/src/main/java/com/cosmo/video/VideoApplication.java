package com.cosmo.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VideoApplication {



    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }
}
