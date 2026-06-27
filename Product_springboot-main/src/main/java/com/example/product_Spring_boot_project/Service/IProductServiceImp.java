package com.example.product_Spring_boot_project.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.product_Spring_boot_project.Repository.ProductRepo;
import com.example.product_Spring_boot_project.dto.InputDto;
import com.example.product_Spring_boot_project.dto.ViewDto;
import com.example.product_Spring_boot_project.model.Product;

@Service
public class IProductServiceImp implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Override
	public ViewDto createProduct(InputDto dto) {
		// TODO Auto-generated method stub
//		return null;
		Product Entity = toEntity(dto);
		Product saved = productRepo.save(Entity);
		return toViewDto(saved);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ViewDto> GetAllProduct() {
		// TODO Auto-generated method stub
		return productRepo.findAll().stream().map(this::toViewDto).collect(Collectors.toList());
		
	}

	@Override
	public ViewDto GetProductById(Long id) {
		// TODO Auto-generated method stub
//		return null;
		Product p= productRepo.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return toViewDto(p);
	}

//	@Override
//	public EarningDto DeleteProductById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ViewDto UpdateProduct(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		productRepo.deleteById(id);
	}

//	helper method 
	private Product toEntity(InputDto dto) {
		Product p = new Product();
		p.setProductName(dto.getProductName());
		p.setBrandName(dto.getBrandName());
		p.setDescription(dto.getDescription());
		p.setPrice(dto.getPrice());
		p.setRetailCost(dto.getRetailCost());
		p.setSellingCost(dto.getSellingCost());
		p.setProfitOnProduct(dto.getProfitOnProduct());
		p.validate();
		return p;
	}

	private ViewDto toViewDto(Product p) {
		BigDecimal profit = null;
		if (p.getSellingCost() != null && p.getRetailCost() != null) {
			profit = p.getSellingCost().subtract(p.getRetailCost());

		} else {
			profit = p.getProfitOnProduct();
		}
		return new ViewDto (
				p.getId(),
				p.getProductName(),
				p.getBrandName(),
				p.getDescription(),
				p.getPrice(),
				p.getRetailCost(),
				p.getSellingCost(),
				profit);
	}
}