package org.tbank.restkudago.model;

import lombok.Data;

@Data
public class Location {
    private Long id;
    private String slug;
    private String name;
    private String timezone;
    private Coords coords;
    private String language;

    public Location(String slug, String name, String timezone, Coords coords, String language) {
        this.slug = slug;
        this.name = name;
        this.timezone = timezone;
        this.coords = coords;
        this.language = language;
    }
}
