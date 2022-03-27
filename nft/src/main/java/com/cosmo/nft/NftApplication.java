package com.cosmo.nft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class NftApplication {

    public static void main(String[] args) {
        SpringApplication.run(NftApplication.class, args);
    }
}
