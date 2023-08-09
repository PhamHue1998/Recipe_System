package com.nal.teamc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nal.teamc.dtos.CategoryDTO;
import com.nal.teamc.enties.Category;
import com.nal.teamc.services.ICategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> categoryDTOs = categoryService.getCategories();
		return ResponseEntity.ok(categoryDTOs);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id) {
    	CategoryDTO categoryDTO = categoryService.findById(id);
        if (categoryDTO != null) {
            return ResponseEntity.ok(categoryDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
