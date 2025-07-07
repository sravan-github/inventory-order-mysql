package com.example.orderservice.client;

import com.example.orderservice.dto.InventoryItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "${inventory-service.url}")
public interface InventoryClient {

    @GetMapping("/{id}")
    InventoryItemDto getItemById(@PathVariable("id") Long id);
}
