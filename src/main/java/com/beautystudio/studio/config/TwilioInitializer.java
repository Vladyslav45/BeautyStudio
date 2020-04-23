package com.beautystudio.studio.config;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Autowired
    public void init(){
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
    }
}
