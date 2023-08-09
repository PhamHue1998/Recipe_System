package com.nal.teamc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nal.teamc.dtos.CategoryDTO;
import com.nal.teamc.reponsives.PostCategoryResponsitory;

@Service
public class PostCategoryService implements IPostCategoryService{
	
	@Autowired
	private PostCategoryResponsitory responsitory;

	@Override
	public List<CategoryDTO> getCategoriesByPostId(int postId) {
        List<Object[]> results = responsitory.findByPostId(postId);
        List<CategoryDTO> dtos = new ArrayList<>();

        for (Object[] result : results) {
            Integer categoryId = (Integer) result[0];
            String categoryName = (String) result[1];
            String categoryImg = (String) result[2];

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categoryId);
            categoryDTO.setCategoryName(categoryName);
            categoryDTO.setCategoryImg(categoryImg);

            dtos.add(categoryDTO);
        }

        return dtos;
	}
}
