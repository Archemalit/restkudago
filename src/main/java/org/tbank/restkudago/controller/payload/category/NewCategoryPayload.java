package org.tbank.restkudago.controller.payload.category;

import jakarta.validation.constraints.NotBlank;

public record NewCategoryPayload (
        @NotBlank String slug,
        @NotBlank String name
) {}
