package com.qasandbox.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class ExternalProfileConfig {

    @Bean
    public RestClient externalProfileRestClient(
            RestClient.Builder builder,
            @Value("${qa.external-profile.base-url}") String baseUrl,
            @Value("${qa.external-profile.timeout}") Duration timeout
    ) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(timeout);
        requestFactory.setReadTimeout(timeout);
        return builder.baseUrl(baseUrl).requestFactory(requestFactory).build();
    }
}
