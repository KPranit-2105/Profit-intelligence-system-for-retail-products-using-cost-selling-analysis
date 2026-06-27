package com.example.product_Spring_boot_project.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Product name
	@NotBlank(message = "Product name must not be blank.")
	@Size(max = 150, message = "Product name must be at most 150 characters.")
	@Column(name = "product_name", nullable = false, length = 150)
	private String productName;

	// Description (optional)
	@Size(max = 1000, message = "Description must be at most 1000 characters.")
	@Column(name = "description", length = 1000)
	private String description;

	// Base price (optional depending on your domain)
	@DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Price must be a valid amount with up to 2 decimal places.")
//	@Column(name = "price", precision = 14, scale = 2)
	private BigDecimal price;

	// Retail cost (your buy/inventory cost)
	@NotNull(message = "Retail cost must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Retail cost must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Retail cost must be a valid amount with up to 2 decimal places.")
//	@Column(name = "retail_cost", nullable = false, precision = 14, scale = 2)
	private BigDecimal retailCost;

	// Selling cost (your selling price)
	@NotNull(message = "Selling cost must not be null.")
	@DecimalMin(value = "0.0", inclusive = true, message = "Selling cost must be non-negative.")
//	@Digits(integer = 12, fraction = 2, message = "Selling cost must be a valid amount with up to 2 decimal places.")
//	@Column(name = "selling_cost", nullable = false, precision = 14, scale = 2)
	private BigDecimal sellingCost;

	// Brand
	@NotBlank(message = "Brand name must not be blank.")
	@Size(max = 100, message = "Brand name must be at most 100 characters.")
//	@Column(name = "brand_name", nullable = false, length = 100)
	private String brandName;

	// Profit on product (optional field; can be computed)
	@DecimalMin(value = "0.0", inclusive = false, message = "Profit must be greater than 0 if provided.")
	@Digits(integer = 12, fraction = 2, message = "Profit must be a valid amount with up to 2 decimal places.")
//	@Column(name = "profit_on_product", precision = 14, scale = 2)
	private BigDecimal profitOnProduct;

	// ----- Constructors -----

	

	public Product() {
	}

	public Product(Long id,
			@NotBlank(message = "Product name must not be blank.") @Size(max = 150, message = "Product name must be at most 150 characters.") String productName,
			@Size(max = 1000, message = "Description must be at most 1000 characters.") String description,
			@DecimalMin(value = "0.0", inclusive = true, message = "Price must be non-negative.") BigDecimal price,
			@NotNull(message = "Retail cost must not be null.") @DecimalMin(value = "0.0", inclusive = true, message = "Retail cost must be non-negative.") BigDecimal retailCost,
			@NotNull(message = "Selling cost must not be null.") @DecimalMin(value = "0.0", inclusive = true, message = "Selling cost must be non-negative.") BigDecimal sellingCost,
			@NotBlank(message = "Brand name must not be blank.") @Size(max = 100, message = "Brand name must be at most 100 characters.") String brandName,
			@DecimalMin(value = "0.0", inclusive = false, message = "Profit must be greater than 0 if provided.") @Digits(integer = 12, fraction = 2, message = "Profit must be a valid amount with up to 2 decimal places.") BigDecimal profitOnProduct) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.retailCost = retailCost;
		this.sellingCost = sellingCost;
		this.brandName = brandName;
		this.profitOnProduct = profitOnProduct;
	}

	/**
	 * Basic validation beyond annotations (optional). Throws
	 * IllegalArgumentException if invalid.
	 */
	public void validate() {
		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name must not be blank.");
		}
		if (brandName == null || brandName.trim().isEmpty()) {
			throw new IllegalArgumentException("Brand name must not be blank.");
		}
		if (retailCost == null || retailCost.signum() < 0) {
			throw new IllegalArgumentException("Retail cost must be non-negative and not null.");
		}
		if (sellingCost == null || sellingCost.signum() < 0) {
			throw new IllegalArgumentException("Selling cost must be non-negative and not null.");
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getProfitOnProduct() {
		return profitOnProduct;
	}

	public void setProfitOnProduct(BigDecimal profitOnProduct) {
		this.profitOnProduct = profitOnProduct;
	}
}