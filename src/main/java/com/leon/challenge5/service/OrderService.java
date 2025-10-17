package com.leon.challenge5.service;

import com.leon.challenge5.dto.OrderRequest;
import com.leon.challenge5.dto.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse create(OrderRequest req);
    OrderResponse get(Long id);
    Page<OrderResponse> list(Pageable pageable);
    OrderResponse update(Long id, OrderRequest req);
    void delete(Long id);
}
