package com.nal.teamc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nal.teamc.dtos.AddPostDTO;
import com.nal.teamc.dtos.PostDTO;
import com.nal.teamc.enties.Image;
import com.nal.teamc.enties.Post;
import com.nal.teamc.enties.User;
import com.nal.teamc.reponsives.CategoryReponsitory;
import com.nal.teamc.reponsives.PostRepository;
import com.nal.teamc.reponsives.UserRepository;

@Service
public class PostService implements IPostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CategoryReponsitory categoryReponsitory;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<PostDTO> getAllPosts(Pageable pageable) {
		Page<Post> listPost = postRepository.findAll(pageable);

		// Tạo listPostDTO
		List<PostDTO> listPostDTO = new ArrayList<>();

		// Covert từng bài post sang postDTO trong danh sách listPost
		for (Post post : listPost) {
			PostDTO postDTO = covertPostToPostDTO(post);
			listPostDTO.add(postDTO);
		}

		// Trả về 1 Page<PostDTO> ( với thông tin phân trang, và tổng phần tử trong danh
		// sách listPost)
		return new PageImpl<>(listPostDTO, pageable, listPost.getTotalElements());
	}

	@Override
	public Page<PostDTO> getPostByTitle(String title, Pageable pageable) {
		Page<Post> listPost = postRepository.findByTitleContaining(title, pageable);

		// Tạo listPostDTO
		List<PostDTO> listPostDTO = new ArrayList<>();

		// Covert từng bài post sang postDTO trong danh sách listPost
		for (Post post : listPost) {
			PostDTO postDTO = covertPostToPostDTO(post);
			listPostDTO.add(postDTO);
		}

		// Trả về 1 Page<PostDTO> ( với thông tin phân trang, và tổng phần tử trong danh
		// sách listPost)
		return new PageImpl<>(listPostDTO, pageable, listPost.getTotalElements());
	}

	// Hàm chuyển đổi Post sang PostDTO
	private PostDTO covertPostToPostDTO(Post post) {
		PostDTO dto = new PostDTO();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setContent(post.getContent());
		dto.setLikeNumber(post.getLikeNumber());
		List<String> imageUrls = new ArrayList<>();
		for (Image image : post.getImages()) {
			imageUrls.add(image.getImage_url());
		}
		dto.setImageUrls(imageUrls);
		return dto;
	}

	// Thêm bài viết vào category
	@Override
	public Post addPost(AddPostDTO addPostDTO, int userId) {
		Post newPost = new Post();
		User user = userRepository.findById(userId);

		if (user != null) {
			newPost.setUser(user);
			newPost.setTitle(addPostDTO.getTitle());
			newPost.setContent(addPostDTO.getContent());
			// Chuyển đổi danh sách ảnh từ DTO sang Entity
			List<Image> listImage = new ArrayList<>();
			for (String imageUrl : addPostDTO.getImageUrls()) {
				Image image = new Image();
				image.setImage_url(imageUrl);
				// set image vào listImage
				listImage.add(image);
			}
			newPost.setImages(listImage);
			newPost.setCreatedAt(new Date());

			// lưu bài viết vào db
			postRepository.save(newPost);
			return newPost;
		} else {
			return null;
		}

	}

	/**
	 * Update like numbers of post
	 */
	@Override
	public Integer addLike2Post(int postId) {

		//Get post by id
		Post cPost = postRepository.findById(postId).get();

		// If post is exist
		if (!Objects.isNull(cPost)) {
			//Count post like and plus 1
			int nLike = cPost.getLikeNumber() + 1;
			
			//Update new like numbers
			cPost.setLikeNumber(nLike);
			postRepository.save(cPost);
			
			//return new like numnbers
			return nLike;
		}

		return 0;

	}

	// Lấy ra 5 bài post mới đăng
	@Override
	public List<PostDTO> getLatestFivePosts() {
        List<Post> latestPosts = postRepository.findTop5ByOrderByCreatedAtDesc();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : latestPosts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setLikeNumber(post.getLikeNumber());

            // Thêm imageUrls vào PostDTO
            List<String> imageUrls = new ArrayList<>();
            for (Image image : post.getImages()) {
                imageUrls.add(image.getImage_url());
            }
            postDTO.setImageUrls(imageUrls);
            postDTOs.add(postDTO);
        }
        return postDTOs;
	}
	
	// lôi database từ reponsitory cho databse vào list object mostlikedPosts
	public List<PostDTO> getMostLikedPosts() {
        List<Object[]> mostLikedAndCommentedPosts = postRepository.findTop3PostsByLikes();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Object[] data : mostLikedAndCommentedPosts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId((Integer) data[0]);
            postDTO.setTitle((String) data[1]);
            postDTO.setContent((String) data[2]);
            postDTO.setLikeNumber((Integer) data[3]);

            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

	@Override
	public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id).get();
        if(post!=null) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setContent(post.getContent());
            postDTO.setLikeNumber(post.getLikeNumber());
            // Thêm imageUrls vào PostDTO 
            List<String> imageUrls = new ArrayList<>();
            for (Image image : post.getImages()) {
                imageUrls.add(image.getImage_url());
            }
            postDTO.setImageUrls(imageUrls);
            return postDTO;
        }else {
        	return null;
        }
	}
	
    // Lấy danh sách bài post theo category id và cùng với danh sách ảnh trong bài post đó
    public List<PostDTO> getPostsByCategoryId(int categoryId) {
        List<Post> posts = postRepository.findByPostCategories_CategoryId(categoryId);
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = convertToDTO(post);
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }

    // Helper method để convert từ Post sang PostDTO
    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setLikeNumber(post.getLikeNumber());

        // Lấy danh sách ảnh trong bài post
        List<String> imageUrls = post.getImages()
                .stream()
                .map(Image::getImage_url)
                .collect(Collectors.toList());
        postDTO.setImageUrls(imageUrls);

        return postDTO;
    }

}
