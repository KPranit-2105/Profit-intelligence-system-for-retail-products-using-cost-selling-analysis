package com.example.product_Spring_boot_project.record;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductInputDto1(@NotBlank String productName, String description, BigDecimal price,
		@NotNull BigDecimal retailCost, @NotNull BigDecimal sellingCost, String brandName) {

}
