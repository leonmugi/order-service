package com.leon.challenge5.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerId,
        List<OrderItemDto> items,
        BigDecimal totalAmount,
        String status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
