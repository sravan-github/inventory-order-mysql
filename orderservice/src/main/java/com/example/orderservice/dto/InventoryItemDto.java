package com.example.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemDto {
    private Long id;
    private String skuCode;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
