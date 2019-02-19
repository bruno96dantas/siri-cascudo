package com.nogueira.krusty.krab.integration.configuration;

import com.nogueira.krusty.krab.client.KrustyKrabClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@TestConfiguration
public class KrustyKrabTestConfiguration {

    @Bean
    public KrustyKrabClient client(@Qualifier("testRestTemplate") RestTemplate client) {
        return new KrustyKrabClient(client);
    }

    @Bean(name = "testRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
