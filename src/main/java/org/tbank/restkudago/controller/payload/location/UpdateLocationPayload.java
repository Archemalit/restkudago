package org.tbank.restkudago.controller.payload.location;

import org.tbank.restkudago.model.Coords;

public record UpdateLocationPayload(String slug, String name, String timezone, Coords coords, String language) {}
