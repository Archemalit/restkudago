package org.tbank.restkudago.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.tbank.restkudago.controller.payload.location.NewLocationPayload;
import org.tbank.restkudago.controller.payload.location.UpdateLocationPayload;
import org.tbank.restkudago.model.Location;
import org.tbank.restkudago.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationRestController {
    private final LocationService locationService;

    @GetMapping
    public List<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/{locationId}")
    public Location getLocation(@PathVariable Long locationId) {
        return locationService.getLocationById(locationId);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@Valid @RequestBody NewLocationPayload info, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Location location = locationService.createLocation(info.slug(), info.name(), info.timezone(), info.coords(), info.language());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/api/v1/locations/{locationId}")
                            .build(location.getId()))
                    .body(location);
        }
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Void> updateLocation(@Valid @RequestBody UpdateLocationPayload info, @PathVariable Long locationId, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            locationService.updateLocation(locationId, info.slug(), info.name(), info.timezone(), info.coords(), info.language());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.noContent().build();
    }
}
