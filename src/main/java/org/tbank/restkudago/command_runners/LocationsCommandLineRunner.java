package org.tbank.restkudago.command_runners;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tbank.restkudago.clients.InterfaceRestClientLocations;
import org.tbank.restkudago.model.Location;
import org.tbank.restkudago.repository.impl.ConcurentHashMapLocationRepositoryImpl;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationsCommandLineRunner implements CommandLineRunner {
    private final InterfaceRestClientLocations restClientLocations;
    private final ConcurentHashMapLocationRepositoryImpl locationRepository;

    @Override
    public void run(String... args) {
        List<Location> locations = restClientLocations.findAllLocations("ru", "slug", List.of("slug", "name", "timezone", "coords", "language"));
        locationRepository.initLocations(locations);
    }
}
