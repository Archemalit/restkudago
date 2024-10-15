package org.tbank.restkudago.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.tbank.restkudago.model.Coords;
import org.tbank.restkudago.model.Location;
import org.tbank.restkudago.service.LocationService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(controllers = {LocationRestController.class})
public class LocationRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LocationService locationService;

    @Test
    @DisplayName("Testing of getting of all locations")
    public void testGetAllLocations() throws Exception {
        // given
        List<Location> locations = List.of(
                new Location("ufa", "Ufa", "GMT+01:00", new Coords(23.12333521416, 54.24234256797), "ru"),
                new Location("moscow", "Moscow", "GMT+03:00", new Coords(75.12312312457, 21.21314555423), "ru")
        );

        when(locationService.findAllLocations()).thenReturn(locations);

        // when
        mockMvc.perform(get("/api/v1/locations"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"id\":null,\"slug\":\"ufa\",\"name\":\"Ufa\",\"timezone\":\"GMT+01:00\",\"coords\":{\"lat\":23.12333521416,\"lon\":54.24234256797},\"language\":\"ru\"},{\"id\":null,\"slug\":\"moscow\",\"name\":\"Moscow\",\"timezone\":\"GMT+03:00\",\"coords\":{\"lat\":75.12312312457,\"lon\":21.21314555423},\"language\":\"ru\"}]"));
    }

    @Test
    @DisplayName("Testing of getting the location by id")
    public void testGetLocationById() throws Exception {
        // given
        String payload = """
                        "id": 1,
                        "slug": "ufa",
                        "name": "Ufa",
                        "timezone": "GMT+01:00",
                        "coords": {
                            "lat": 23.12333521416,
                            "lon": 54.24234256797
                        },
                        "language": "ru"
                """;
        Long locationId = 1L;
        Location location = new Location("ufa", "Ufa", "GMT+01:00", new Coords(23.12333521416, 54.24234256797), "ru");
        location.setId(locationId);
        when(locationService.getLocationById(locationId)).thenReturn(location);

        // when
        mockMvc.perform(get("/api/v1/locations/{locationId}", locationId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"slug\":\"ufa\",\"name\":\"Ufa\",\"timezone\":\"GMT+01:00\",\"coords\":{\"lat\":23.12333521416,\"lon\":54.24234256797},\"language\":\"ru\"}"));

    }

    @Test
    @DisplayName("Testing of creatig new location")
    public void testCreateLocation() throws Exception {
        // given
        String payload = """
                    {
                        "slug": "ufa",
                        "name": "Ufa",
                        "timezone": "GMT+01:00",
                        "coords": {
                            "lat": 23.12333521416,
                            "lon": 54.24234256797
                        },
                        "language": "ru"
                    }
                """;

        Location location = new Location("ufa", "Ufa", "GMT+01:00", new Coords(23.12333521416, 54.24234256797), "ru");
        location.setId(1L);
        when(locationService.createLocation("ufa", "Ufa", "GMT+01:00", new Coords(23.12333521416, 54.24234256797), "ru")).thenReturn(location);

        // when
        mockMvc.perform(post("/api/v1/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1/locations/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"id\":1,\"slug\":\"ufa\",\"name\":\"Ufa\",\"timezone\":\"GMT+01:00\",\"coords\":{\"lat\":23.12333521416,\"lon\":54.24234256797},\"language\":\"ru\"}"));
    }

    @Test
    @DisplayName("Testing of updating location by id")
    public void testUpdateLocation() throws Exception {
        // given
        String payload = """
                    {
                        "slug": "ufa",
                        "name": "Ufa",
                        "timezone": "GMT+01:00",
                        "coords": {
                            "lat": 23.12333521416,
                            "lon": 54.24234256797
                        },
                        "language": "ru"
                    }
                """;
        Long locationId = 1L;
        Location location = new Location("ufa", "Ufa", "GMT+01:00", new Coords(23.12333521416, 54.24234256797), "ru");
        location.setId(locationId);
        when(locationService.getLocationById(locationId)).thenReturn(location);

        // when
        mockMvc.perform(put("/api/v1/locations/{locationId}", locationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Testing of deleting location by id")
    public void testDeleteLocation() throws Exception {
        Long locationId = 1L;
        mockMvc.perform(delete("/api/v1/locations/{locationId}", locationId))
                .andExpect(status().isNoContent());
    }
}
