package org.tbank.restkudago.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tbank.restkudago.model.Coords;
import org.tbank.restkudago.model.Location;
import org.tbank.restkudago.repository.LocationRepository;
import org.tbank.restkudago.service.LocationService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Long locationId) {
        return locationRepository.getById(locationId).orElseThrow(() -> new NoSuchElementException("Location with id=" + locationId + "not found!"));
    }

    @Override
    public Location createLocation(String slug, String name, String timezone, Coords coords, String language) {
        return locationRepository.save(new Location(slug, name, timezone, coords, language));
    }

    @Override
    public void updateLocation(Long locationId, String slug, String name, String timezone, Coords coords, String language) {
        Location location = getLocationById(locationId);
        location.setSlug(slug);
        location.setName(name);
        location.setTimezone(timezone);
        location.setCoords(coords);
        location.setLanguage(language);
        locationRepository.updateById(locationId, location);
    }

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }
}
