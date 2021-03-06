package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.PostCreateDto;
import com.fsega.animalbrokers.model.dto.PostDto;
import com.fsega.animalbrokers.model.dto.PostSearchDto;
import com.fsega.animalbrokers.service.PostService;
import com.fsega.animalbrokers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("@userService.isLoggedInUserActive()")
    public PostDto createPost(@RequestBody @Valid PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto);
    }

    @GetMapping
    public List<PostDto> searchPosts(@ModelAttribute PostSearchDto postSearchDto) {
        return postService.searchPosts(postSearchDto);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("@postService.isLoggedInUserCreatorOfPost(#postId) and " +
            "@userService.isLoggedInUserActive()")
    public PostDto updatePost(@PathVariable UUID postId, @RequestBody @Valid PostCreateDto postCreateDto) {
        return postService.updatePost(postId, postCreateDto);
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or " +
            "(@postService.isLoggedInUserCreatorOfPost(#postId) and " +
            "@userService.isLoggedInUserActive())")
    public Boolean deletePost(@PathVariable UUID postId) {
        return postService.deletePost(postId);
    }

}
