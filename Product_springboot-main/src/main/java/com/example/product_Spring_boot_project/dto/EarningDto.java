package com.example.product_Spring_boot_project.dto;

import java.math.BigDecimal;

public class EarningDto {
	

	private BigDecimal profitOnProduct;

	public BigDecimal getProfitOnProduct() {
		return profitOnProduct;
	}

	public void setProfitOnProduct(BigDecimal profitOnProduct) {
		this.profitOnProduct = profitOnProduct;
	}

	public EarningDto(BigDecimal profitOnProduct) {
		this.profitOnProduct = profitOnProduct;
	}

	public EarningDto() {
		super();
	}
}
