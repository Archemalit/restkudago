package org.tbank.restkudago.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.tbank.restkudago.clients.impl.RestClientCategories;
import org.tbank.restkudago.clients.impl.RestClientLocations;

@Configuration
public class ClientBeans {
    @Bean
    public RestClientCategories restClientCategories(@Value("${base.url.categories}") String baseUrl) {
        return new RestClientCategories(
            RestClient
                    .builder()
                    .baseUrl(baseUrl)
                    .build()
        );
    }

    @Bean
    public RestClientLocations restClientLocations(@Value("${base.url.locations}") String baseUrl) {
        return new RestClientLocations(
                RestClient
                        .builder()
                        .baseUrl(baseUrl)
                        .build()
        );
    }
}
