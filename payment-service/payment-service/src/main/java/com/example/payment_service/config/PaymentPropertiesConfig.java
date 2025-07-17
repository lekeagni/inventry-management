package com.example.payment_service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.StandardException;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "payment")
@Getter
@Setter
public class PaymentPropertiesConfig {

    private Paypal paypal;
    private Momo momo;
    private Orange orange;

    @Getter
    @Setter
    public static class Paypal{
        private String clientId;
        private String clientSecret;
    }

    @Getter
    @Setter
    public static class Momo{
        private String apiKey;
        private String apiSecret;
    }

    @Getter
    @Setter
    public static class Orange{
        private String apiKey;
        private String apiSecret;
    }
}
