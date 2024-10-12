package org.tbank.restkudago.service;

import org.tbank.restkudago.model.Category;
import org.tbank.restkudago.model.Coords;
import org.tbank.restkudago.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAllLocations();
    Location getLocationById(Long locationId);
    Location createLocation(String slug, String name, String timezone, Coords coords, String language);
    void updateLocation(Long locationId, String slug, String name, String timezone, Coords coords, String language);
    void deleteLocation(Long locationId);
}
