package com.example.product_Spring_boot_project.record;

import java.math.BigDecimal;

public record ProductViewDto(Long id, String productName, String description, BigDecimal price, BigDecimal retailCost,
		BigDecimal sellingCost, String brandName, BigDecimal profitOnProduct) {

}