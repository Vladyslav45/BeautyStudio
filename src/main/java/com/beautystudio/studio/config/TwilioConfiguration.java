package com.beautystudio.studio.config;

import com.twilio.Twilio;
import org.springframework.stereotype.Component;

@Component
public class TwilioConfiguration {
    private static final String ACCOUNT_SID = "AC5fb0a7cdbecf4a2deb0dc46f72679537";
    private static final String AUTH_TOKEN = "31682dbe14f581c6eae533b2e006d678";

    public void init(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
}
