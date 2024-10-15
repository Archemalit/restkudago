package org.tbank.restkudago.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.tbank.restkudago.controller.CategoryRestController;
import org.tbank.restkudago.controller.LocationRestController;
import org.tbank.restkudago.service.CategoryService;
import org.tbank.restkudago.service.LocationService;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CategoryRestController.class, LocationRestController.class})
public class GlobalExceptionHandlerControllerAdviceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private LocationService locationService;

    @Test
    public void testBindExceptionInCategory() throws Exception {
        // given
        String payload = """
                {
                    "sl" : "cinema",
                    "name" : "Cinema in Surf Coffee"
                }
                """;

        // when
        mockMvc.perform(post("/api/v1/places/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testBindExceptionInLocation() throws Exception {
        // given
        String payload = """
                    {
                        "sl": "ufa",
                        "name": "Ufa",
                        "timezone": "GMT+01:00",
                        "coords": {
                            "lat": 23.12333521416,
                            "lon": 54.24234256797
                        },
                        "language": "ru"
                    }
                """;

        // when
        mockMvc.perform(post("/api/v1/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNoSuchElementInCategory() throws Exception {
        // given
        Long categoryId = 1L;

        when(categoryService.getCategoryById(categoryId)).thenThrow(NoSuchElementException.class);

        // when
        mockMvc.perform(get("/api/v1/places/categories/{categoryId}", categoryId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testNoSuchElementInLocation() throws Exception {
        // given
        Long locationId = 1L;

        when(locationService.getLocationById(locationId)).thenThrow(NoSuchElementException.class);

        // when
        mockMvc.perform(get("/api/v1/locations/{locationId}", locationId))
                .andExpect(status().isNotFound());
    }


}
