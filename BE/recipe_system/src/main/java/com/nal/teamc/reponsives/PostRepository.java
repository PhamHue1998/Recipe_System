package com.nal.teamc.reponsives;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	// Lấy danh sách post
	Page<Post> findAll(Pageable pageable);
	
	// Tìm kiếm theo title của post (Tìm kiếm gần đúng)
	Page<Post> findByTitleContaining(String title, Pageable pageable);
	
	// Lấy ra danh sách 5 bài viêt mới nhất
	List<Post> findTop5ByOrderByCreatedAtDesc();
	
	// tìm 3 bài viết có lượt like và comment nhiều nhất lệnh dtdb
	@Query(value = "SELECT recipe_system.tb_posts.id, recipe_system.tb_posts.title,recipe_system.tb_posts.content,recipe_system.tb_posts.like_number\r\n"
			+ " FROM recipe_system.tb_posts \r\n"
			+ " ORDER BY recipe_system.tb_posts.like_number DESC LIMIT 3",nativeQuery=true)
	// Dùng list object để lưu các dữ liệu từ database
	List<Object[]> findTop3PostsByLikes();
	
    // Tìm các bài post theo category id
    List<Post> findByPostCategories_CategoryId(int categoryId);
	
}
