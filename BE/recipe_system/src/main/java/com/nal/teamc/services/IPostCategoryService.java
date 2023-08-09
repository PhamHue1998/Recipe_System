package com.nal.teamc.services;

import java.util.List;

import com.nal.teamc.dtos.CategoryDTO;

public interface IPostCategoryService {

	List<CategoryDTO> getCategoriesByPostId(int postId);

}
