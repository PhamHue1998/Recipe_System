package com.nal.teamc.reponsives;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nal.teamc.enties.SavePost;

@Repository
public interface SavedPostRepository extends JpaRepository<SavePost, Integer>{

}
