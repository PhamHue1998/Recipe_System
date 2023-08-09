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
import com.nal.teamc.services.IPostCategoryService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class PostCategoryController {
	@Autowired
	private IPostCategoryService postCategoryService;
	
    @GetMapping("/post/{postId}/categories")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByPostId(@PathVariable int postId) {
        List<CategoryDTO> categories = postCategoryService.getCategoriesByPostId(postId);
        return ResponseEntity.ok(categories);
    }
}
