package com.example.product_Spring_boot_project.Service;

import java.util.List;

import com.example.product_Spring_boot_project.dto.InputDto;
import com.example.product_Spring_boot_project.dto.ViewDto;

public interface ProductService {
	 ViewDto createProduct(InputDto dto);
	List<ViewDto> GetAllProduct();
	
	ViewDto GetProductById(Long id);
//	EarningDto DeleteProductById(Long id);
	ViewDto UpdateProduct(Long id);
	void deleteProduct(Long id);
//	 ProductViewDto createProduct(ProductInputDto dto);
//		List<ProductViewDto> getAllProducts();
//		ProductViewDto getProductsById(Long id);
//		ProductEarningDto getProductEarning(Long id);
//		void deleteProduct(Long id);
	
}
