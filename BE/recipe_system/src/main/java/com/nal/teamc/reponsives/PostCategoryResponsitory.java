package com.nal.teamc.reponsives;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.PostCategory;

@Repository
public interface PostCategoryResponsitory extends JpaRepository<PostCategory, Integer>{
    @Query("SELECT c.id, c.categoryName, c.categoryImg "
            + "FROM PostCategory pc JOIN Category c "
            + "ON pc.category.id = c.id "
            + "WHERE pc.post.id = ?1")
     List<Object[]> findByPostId(int postId);
}
