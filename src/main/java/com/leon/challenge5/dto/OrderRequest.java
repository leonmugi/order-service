package com.leon.challenge5.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record OrderRequest(
        @NotBlank String customerId,
        @NotEmpty List<OrderItemDto> items
) {}
