package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.*;
import com.fsega.animalbrokers.model.entity.Post;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.entity.User;
import com.fsega.animalbrokers.repository.PostRepository;
import com.fsega.animalbrokers.repository.ThreadRepository;
import com.fsega.animalbrokers.repository.UserRepository;
import com.fsega.animalbrokers.security.services.UserDetailsImpl;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.exception.NotFoundException;
import com.fsega.animalbrokers.utils.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fsega.animalbrokers.utils.mapper.PhotoMapper.toEntities;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepo;
    private final ThreadRepository threadRepo;
    private final UserRepository userRepo;

    public PostDto createPost(PostCreateDto postCreateDto) {
        Thread thread = threadRepo.getOne(postCreateDto.getThreadId());
        User poster = userRepo.getOne(postCreateDto.getPosterId());

        Post post = PostMapper.toEntity(postCreateDto, thread, poster);
        post.getPhotos().forEach(photo -> photo.setPost(post));
        return PostMapper.toDto(postRepo.save(post));
    }

    @Transactional(readOnly = true)
    public List<PostDto> searchPosts(PostSearchDto postSearchDto) {
        return postRepo.searchPosts(postSearchDto.getPosterId(), postSearchDto.getThreadId())
                .stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostDto updatePost(UUID postId, PostCreateDto postCreateDto) {
        Post postToUpdate = postRepo.findPostById(postId);
        if (postToUpdate == null) {
            throw new NotFoundException(String.format("Post with id: %s not found.", postId),
                    ExceptionType.NOT_FOUND);
        }
        if (postCreateDto.getPhotos() != null && !postCreateDto.getPhotos().isEmpty()) {
            postToUpdate.setPhotos(toEntities(postCreateDto.getPhotos()));
        }
        postToUpdate.setText(postCreateDto.getText());
        return PostMapper.toDto(postRepo.save(postToUpdate));
    }

    @Transactional
    public Boolean deletePost(UUID postId) {
        Post postToDelete = postRepo.findPostById(postId);
        if (postToDelete == null) {
            throw new NotFoundException(String.format("Post with id: %s not found.", postId),
                    ExceptionType.NOT_FOUND);
        }

        postRepo.delete(postToDelete);
        return !postRepo.existsById(postId);
    }

    @Transactional(readOnly = true)
    public Boolean isLoggedInUserCreatorOfPost(UUID postId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        UUID loggedInUserId = userDetails.getId();

        return postRepo.existsByIdAndPosterId(postId, loggedInUserId);
    }
}
