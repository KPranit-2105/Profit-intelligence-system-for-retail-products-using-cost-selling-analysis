package com.example.product_Spring_boot_project.dto;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InputDto {

	@NotBlank(message = "Product name must not be blank.")
	@Size(max = 150, message = "Product name must be at most 150 characters.")
	@Column(name = "product_name", nullable = false, length = 150)
	private String productName;

	@NotBlank(message = "Brand name must not be blank.")
	@Size(max = 100, message = "Brand name must be at most 100 characters.")
	@Column(name = "brand_name", nullable = false, length = 100)
	private String brandName;

	@Size(max = 1000, message = "Description must be at most 1000 characters.")
	@Column(name = "description", length = 1000)
	private String description;

	@DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Price must be a valid amount with up to 2 decimal places.")
//	@Column(name = "price", precision = 14, scale = 2)
	private BigDecimal price; // optional base price

	@NotNull(message = "Retail cost must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Retail cost must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Retail cost must be a valid amount with up to 2 decimal places.")
//	@Column(name = "retail_cost", nullable = false, precision = 14, scale = 2)
	private BigDecimal retailCost; // your buy/inventory cost

	@NotNull(message = "Selling cost must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Selling cost must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Selling cost must be a valid amount with up to 2 decimal places.")
//	@Column(name = "selling_cost", nullable = false, precision = 14, scale = 2)
	private BigDecimal sellingCost; // your selling price

	@DecimalMin(value = "0.0", inclusive = false, message = "Profit must be greater than 0 if provided.")
//	@Digits(integer = 12, fraction = 2, message = "Profit must be a valid amount with up to 2 decimal places.")
//	@Column(name = "profit_on_product", precision = 14, scale = 2)
	private BigDecimal profitOnProduct; // sellingCost - retailCost (copied or computed)

	public InputDto(
			@NotBlank(message = "Product name must not be blank.") @Size(max = 150, message = "Product name must be at most 150 characters.") String productName,
			@NotBlank(message = "Brand name must not be blank.") @Size(max = 100, message = "Brand name must be at most 100 characters.") String brandName,
			@Size(max = 1000, message = "Description must be at most 1000 characters.") String description,
			@DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative.") BigDecimal price,
			@NotNull(message = "Retail cost must not be null.") @DecimalMin(value = "0.0", inclusive = true, message = "Retail cost must be non-negative.") BigDecimal retailCost,
			@NotNull(message = "Selling cost must not be null.") @DecimalMin(value = "0.0", inclusive = true, message = "Selling cost must be non-negative.") BigDecimal sellingCost,
			@DecimalMin(value = "0.0", inclusive = false, message = "Profit must be greater than 0 if provided.") BigDecimal profitOnProduct) {

		this.productName = productName;
		this.brandName = brandName;
		this.description = description;
		this.price = price;
		this.retailCost = retailCost;
		this.sellingCost = sellingCost;
		this.profitOnProduct = profitOnProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRetailCost() {
		return retailCost;
	}

	public void setRetailCost(BigDecimal retailCost) {
		this.retailCost = retailCost;
	}

	public BigDecimal getSellingCost() {
		return sellingCost;
	}

	public void setSellingCost(BigDecimal sellingCost) {
		this.sellingCost = sellingCost;
	}

	public BigDecimal getProfitOnProduct() {
		return profitOnProduct;
	}

	public void setProfitOnProduct(BigDecimal profitOnProduct) {
		this.profitOnProduct = profitOnProduct;
	}



	public InputDto() {

	}

}
