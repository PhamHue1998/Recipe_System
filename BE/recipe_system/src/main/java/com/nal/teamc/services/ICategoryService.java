package com.nal.teamc.services;

import java.util.List;

import com.nal.teamc.dtos.CategoryDTO;
import com.nal.teamc.enties.Category;

public interface ICategoryService {
	List<CategoryDTO> getCategories();

	CategoryDTO findById(int id);
}
