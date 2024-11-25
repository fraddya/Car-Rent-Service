package com.unreallabss.carrent.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@ConfigurationProperties("app")
@Configuration
public class PlatformConfig {

    private Crypto crypto;

    @Data
    public static class Crypto {

        private String password;

        private String salt;
    }

}
