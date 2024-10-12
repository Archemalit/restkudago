package org.tbank.restkudago.clients.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.tbank.restkudago.clients.InterfaceRestClientLocations;
import org.tbank.restkudago.model.Location;

import java.util.List;

@RequiredArgsConstructor
public class RestClientLocations implements InterfaceRestClientLocations {
    private static final ParameterizedTypeReference<List<Location>> LOCATIONS_TYPE_REFERENCE = new ParameterizedTypeReference<>() {};
    private final RestClient restClient;

    @Override
    public List<Location> findAllLocations(String lang, String orderBy, List<String> fields) {
        List<Location> locations = restClient
                .get()
                .uri(builder -> builder
                        .path("/public-api/v1.4/locations/")
                        .queryParam("lang", lang)
                        .queryParam("order_by", orderBy)
                        .queryParam("fields", String.join(",", fields))
                        .build())
                .retrieve()
                .body(LOCATIONS_TYPE_REFERENCE);
        return locations;
    }
}
