package com.example.product_Spring_boot_project.dto;



import java.math.BigDecimal;

public class ViewDto {

    private Long id;
    private String productName;
    private String brandName;
    private String description;

    private BigDecimal price;        // optional base price
    private BigDecimal retailCost;   // your buy/inventory cost
    private BigDecimal sellingCost;  // your selling price
    private BigDecimal profitOnProduct; // sellingCost - retailCost (copied or computed)



    public ViewDto(Long id, String productName, String brandName, String description, BigDecimal price,
			BigDecimal retailCost, BigDecimal sellingCost, BigDecimal profitOnProduct) {

		this.id = id;
		this.productName = productName;
		this.brandName = brandName;
		this.description = description;
		this.price = price;
		this.retailCost = retailCost;
		this.sellingCost = sellingCost;
		this.profitOnProduct = profitOnProduct;
	}
	public ViewDto() {

	}
	// Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getRetailCost() { return retailCost; }
    public void setRetailCost(BigDecimal retailCost) { this.retailCost = retailCost; }

    public BigDecimal getSellingCost() { return sellingCost; }
    public void setSellingCost(BigDecimal sellingCost) { this.sellingCost = sellingCost; }

    public BigDecimal getProfitOnProduct() { return profitOnProduct; }
    public void setProfitOnProduct(BigDecimal profitOnProduct) { this.profitOnProduct = profitOnProduct; }
}


