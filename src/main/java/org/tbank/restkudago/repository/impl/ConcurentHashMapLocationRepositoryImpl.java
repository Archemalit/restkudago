package org.tbank.restkudago.repository.impl;

import org.springframework.stereotype.Repository;
import org.tbank.restkudago.model.Location;
import org.tbank.restkudago.repository.LocationRepository;

import java.util.List;

@Repository
public class ConcurentHashMapLocationRepositoryImpl extends ConcurentHashMap<Location> implements LocationRepository {

    @Override
    public void initLocations(List<Location> locations) {
        for (Location location : locations) {
            location.setId(lastIndex);
            this.map.put(lastIndex++, location);
        }
    }

    @Override
    public Location save(Location element) {
        element.setId(lastIndex);
        this.map.put(lastIndex++, element);
        return element;
    }
}
