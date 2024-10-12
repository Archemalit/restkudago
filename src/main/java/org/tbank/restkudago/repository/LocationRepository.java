package org.tbank.restkudago.repository;

import org.tbank.restkudago.model.Location;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location> {
    void initLocations(List<Location> locations);
}
