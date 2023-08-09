package com.nal.teamc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nal.teamc.dtos.CategoryDTO;
import com.nal.teamc.enties.Category;
import com.nal.teamc.reponsives.CategoryReponsitory;

@Service
@Transactional
public class CategoryService implements ICategoryService{

	@Autowired
	private CategoryReponsitory categoryReponsitory;
	
	@Override
	public List<CategoryDTO> getCategories() {
		// Lấy danh sách category
		List<Category> categories = categoryReponsitory.findAll();
		
		// Tạo danh sách CategoryDTO rỗng
		List<CategoryDTO> dtos = new ArrayList<>();
		
		// Chuyển danh sách cateory -> categoryDTO
		for (Category category : categories) {
		    CategoryDTO categoryDTO = convertToDTO(category);
		    dtos.add(categoryDTO);
		}
		return dtos;
	}

	// Convert Entity to DTO
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setCategoryName(category.getCategoryName());
        dto.setCategoryImg(category.getCategoryImg());
        return dto;
    }

	@Override
	public CategoryDTO findById(int id) {
		Category category = categoryReponsitory.findById(id);
        if (category != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setCategoryImg(category.getCategoryImg());
            return categoryDTO;
        } else {
            return null;
        }
	}

}
