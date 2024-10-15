package org.tbank.restkudago.model;

import lombok.Data;

@Data
public class Coords {
    private Double lat;
    private Double lon;

    public Coords(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
