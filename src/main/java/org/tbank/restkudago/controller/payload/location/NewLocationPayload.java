package org.tbank.restkudago.controller.payload.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.tbank.restkudago.model.Coords;

public record NewLocationPayload(
        @NotBlank String slug,
        @NotBlank String name,
        @NotBlank String timezone,
        @NotNull Coords coords,
        @NotBlank String language
) {}
