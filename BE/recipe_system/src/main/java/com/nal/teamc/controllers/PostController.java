package com.nal.teamc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nal.teamc.dtos.AddPostDTO;
import com.nal.teamc.dtos.LikePost;
import com.nal.teamc.dtos.PostDTO;
import com.nal.teamc.enties.Post;
import com.nal.teamc.services.IPostService;
import com.nal.teamc.services.ISessionService;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private IPostService postService;

	@Autowired
	private ISessionService sessionService;

	@GetMapping("/posts")
	public ResponseEntity<Page<PostDTO>> getAllPost(Pageable pageable) {
		Page<PostDTO> posts = postService.getAllPosts(pageable);
		return ResponseEntity.ok(posts);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<PostDTO>> getPostByTitle(
			@RequestParam("title") String title,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<PostDTO> pots = postService.getPostByTitle(title, pageable);

		return ResponseEntity.ok(pots);
	}

	@PostMapping
	public ResponseEntity<Post> createPost(@RequestBody AddPostDTO addPostDTO,
			@RequestParam("sessionId") String sessionId) {
		//        // Lấy thông tin đăng nhập của người dùng hiện tại
		//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//        
		//        // Lấy userId từ thông tin đăng nhập
		//        int userId = Integer. valueOf(authentication.getName());
		//        
		// Kiểm tra hạn sử dụng của userId thông qua session
		int userid = sessionService.isSessionExpired(sessionId);

		// Nếu như hết hạn 
		if (userid <= 0) {
			// Hết hạn sử dụng, thực hiện logout và chuyển hướng đến trang đăng nhập
			SecurityContextHolder.clearContext();
			//System.out.println("ass");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		}

		// Trường hợp còn hạn
		// Tạo bài viết
		Post createdPostDTO = postService.addPost(addPostDTO, userid);
		return ResponseEntity.ok(createdPostDTO);
	}

	@PostMapping("/like")
	public ResponseEntity<Integer> likePost(@RequestBody LikePost likePost,
			@RequestParam("sessionId") String sessionId) {

		// Kiểm tra hạn sử dụng của userId thông qua session
		int userid = sessionService.isSessionExpired(sessionId);

		// Nếu như hết hạn 
		if (userid <= 0) {
			// Hết hạn sử dụng, thực hiện logout và chuyển hướng đến trang đăng nhập
			SecurityContextHolder.clearContext();
			System.out.println("ass");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		}

		// Update like numbers of post
		int nLikeNumbers = postService.addLike2Post(likePost.getPostId());
		
		return ResponseEntity.ok(nLikeNumbers);

	}
	
	// Lấy ra 5 bài post mới nhất
    @GetMapping("/new-all-post")
    public ResponseEntity<List<PostDTO>> getLatestPosts() {
        List<PostDTO> latestPosts = postService.getLatestFivePosts();
        return new ResponseEntity<>(latestPosts, HttpStatus.OK);
    }
    
     //Lấy ra các top 3 bài post nhiều like
    @GetMapping("/topPosts")
	public ResponseEntity<List<PostDTO>> getMostLikedPosts() {
		// gọi db từ service r lưu vào list object mostLikedPosts
        List<PostDTO> mostLikedPosts = postService.getMostLikedPosts();
        return ResponseEntity.ok(mostLikedPosts);
    }
    
    // Lấy bài post theo id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO postDTO = postService.getPostById(id);
        if (postDTO != null) {
            return ResponseEntity.ok(postDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // API lấy ra các bài post theo category id và cùng với danh sách ảnh trong bài post đó
    @GetMapping("/category/{categoryId}")
    public List<PostDTO> getPostsByCategoryId(@PathVariable int categoryId) {
        return postService.getPostsByCategoryId(categoryId);
    }

}
