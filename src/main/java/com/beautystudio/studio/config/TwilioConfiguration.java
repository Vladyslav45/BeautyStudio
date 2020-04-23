package com.beautystudio.studio.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
@RequiredArgsConstructor
public class TwilioConfiguration {
    private String accountSid;
    private String authToken;
    private String trialNumber;
}
