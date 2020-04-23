package com.beautystudio.studio;

import com.beautystudio.studio.config.TwilioConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(TwilioConfiguration.class)
public class StudioApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudioApplication.class, args);
    }

}
