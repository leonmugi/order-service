package com.leon.challenge5.service.impl;

import com.leon.challenge5.dto.*;
import com.leon.challenge5.model.*;
import com.leon.challenge5.repository.OrderRepository;
import com.leon.challenge5.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repo;
    public OrderServiceImpl(OrderRepository repo){ this.repo = repo; }

    @Override public OrderResponse create(OrderRequest req){
        Order o = new Order();
        o.setCustomerId(req.customerId());
        o.setStatus("NEW");
        o.setItems(req.items().stream().map(i -> {
            OrderItem it = new OrderItem();
            it.setOrder(o);
            it.setProductId(i.productId());
            it.setQuantity(i.quantity());
            it.setUnitPrice(i.unitPrice());
            return it;
        }).toList());
        o.setTotalAmount(o.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return toResponse(repo.save(o));
    }

    @Override public OrderResponse get(Long id){
        return toResponse(repo.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override public Page<OrderResponse> list(Pageable pageable){
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override public OrderResponse update(Long id, OrderRequest req){
        Order o = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        o.getItems().clear();
        o.setCustomerId(req.customerId());
        o.setItems(req.items().stream().map(i -> {
            OrderItem it = new OrderItem();
            it.setOrder(o);
            it.setProductId(i.productId());
            it.setQuantity(i.quantity());
            it.setUnitPrice(i.unitPrice());
            return it;
        }).toList());
        o.setTotalAmount(o.getItems().stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return toResponse(repo.save(o));
    }

    @Override public void delete(Long id){ repo.deleteById(id); }

    private OrderResponse toResponse(Order o){
        var items = o.getItems().stream()
                .map(i -> new OrderItemDto(i.getProductId(), i.getQuantity(), i.getUnitPrice()))
                .toList();
        return new OrderResponse(o.getId(), o.getCustomerId(), items, o.getTotalAmount(),
                o.getStatus(), o.getCreatedAt(), o.getUpdatedAt());
    }
}
