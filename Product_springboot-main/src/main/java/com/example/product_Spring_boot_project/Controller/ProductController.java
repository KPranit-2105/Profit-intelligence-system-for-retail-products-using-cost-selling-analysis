package com.example.product_Spring_boot_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_Spring_boot_project.Service.ProductService;
import com.example.product_Spring_boot_project.dto.InputDto;
import com.example.product_Spring_boot_project.dto.ViewDto;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ViewDto> createPoduct(@RequestBody InputDto inputdto){
		ViewDto newproduct = productService.createProduct(inputdto);
//		if(newproduct.getId() !=  null) {
//			return new ResponseEntity<ViewDto>(HttpStatus.BAD_REQUEST);
//		}
		return new ResponseEntity<ViewDto>(newproduct,HttpStatus.CREATED);
		
	}
	@GetMapping("/Products")
	public ResponseEntity<List<ViewDto>> getAllProducts(){
		List<ViewDto> getALL = productService.GetAllProduct();
		return new ResponseEntity<List<ViewDto>>(getALL, HttpStatus.OK);
	}
	@GetMapping("/Products/{id}")
	public ResponseEntity<ViewDto> GetProductByid(@PathVariable Long id ){
		ViewDto GetById = productService.GetProductById(id);
		return new ResponseEntity<ViewDto>(GetById, HttpStatus.OK);
	}
}
