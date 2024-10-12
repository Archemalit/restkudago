package org.tbank.restkudago.clients.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.tbank.restkudago.clients.InterfaceRestClientCategories;
import org.tbank.restkudago.model.Category;

import java.util.List;

@RequiredArgsConstructor
public class RestClientCategories implements InterfaceRestClientCategories {
    private static final ParameterizedTypeReference<List<Category>> CATEGORIES_TYPE_REFERENCE = new ParameterizedTypeReference<>() {
    };
    private final RestClient restClient;

    @Override
    public List<Category> findAllCategories(String lang, String orderBy, List<String> fields) {
        List<Category> categories = restClient
                .get()
                .uri(builder -> builder
                        .path("/public-api/v1.4/place-categories/")
                        .queryParam("lang", lang)
                        .queryParam("order_by", orderBy)
                        .queryParam("fields", String.join(",", fields))
                        .build())
                .retrieve()
                .body(CATEGORIES_TYPE_REFERENCE);
        return categories;
    }
}
