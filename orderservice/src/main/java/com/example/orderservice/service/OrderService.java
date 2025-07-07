package com.example.orderservice.service;

import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.dto.InventoryItemDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order placeOrder(Long inventoryItemId, Integer quantity) {
        InventoryItemDto item = inventoryClient.getItemById(inventoryItemId);

        if (item != null && item.getQuantity() >= quantity) {
            // For simplicity, assume inventory is updated manually after order
            Order order = Order.builder()
                    .skuCode(item.getSkuCode())
                    .quantity(quantity)
                    .price(item.getPrice())
                    .status("PLACED")
                    .build();

            return orderRepository.save(order);
        } else {
            return Order.builder()
                    .skuCode(item != null ? item.getSkuCode() : "N/A")
                    .quantity(quantity)
                    .price(item != null ? item.getPrice() : 0.0)
                    .status("FAILED - Not enough stock")
                    .build();
        }
    }
}
