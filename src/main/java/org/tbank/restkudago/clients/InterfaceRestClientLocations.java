package org.tbank.restkudago.clients;

import org.tbank.restkudago.model.Location;

import java.util.List;

public interface InterfaceRestClientLocations {
    List<Location> findAllLocations(String lang, String orderBy, List<String> fields);
}
