package com.nal.teamc.reponsives;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.Category;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category, Integer>{
	List<Category> findAll();
	Category findById(int id);
	List<Category> findAllByIdIn(List<Integer> listId);
}
