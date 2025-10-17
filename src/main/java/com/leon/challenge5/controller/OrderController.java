package com.leon.challenge5.controller;

import com.leon.challenge5.dto.OrderRequest;
import com.leon.challenge5.dto.OrderResponse;
import com.leon.challenge5.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders", description = "CRUD for Orders")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){ this.service = service; }

    @Operation(summary = "Create a new order")
    @PostMapping public OrderResponse create(@Valid @RequestBody OrderRequest req){
        return service.create(req);
    }

    @Operation(summary = "Get an order by id")
    @GetMapping("/{id}") public OrderResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @Operation(summary = "List orders (pagination)")
    @GetMapping public Page<OrderResponse> list(@RequestParam(defaultValue="0") int page,
                                                @RequestParam(defaultValue="10") int size){
        return service.list(PageRequest.of(page, size));
    }

    @Operation(summary = "Update an order")
    @PutMapping("/{id}") public OrderResponse update(@PathVariable Long id,
                                                     @Valid @RequestBody OrderRequest req){
        return service.update(id, req);
    }

    @Operation(summary = "Delete an order")
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
