package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.PostCreateDto;
import com.fsega.animalbrokers.model.dto.PostDto;
import com.fsega.animalbrokers.model.entity.Post;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostMapper {

    public static Post toEntity(PostCreateDto postCreateDto, Thread thread, User poster) {
        if (postCreateDto == null) {
            throw new IllegalArgumentException("Post mapper received null dto as input.");
        }
        return Post.builder()
                .thread(thread)
                .text(postCreateDto.getText())
                .poster(poster)
                .photos(postCreateDto.getPhotos())
                .build();
    }

    public static PostDto toDto(Post post) {
        if (post == null) {
            return null;
        }
        return PostDto.builder()
                .text(post.getText())
                .photos(post.getPhotos())
                .poster(UserMapper.toDto(post.getPoster()))
                .build();
    }

}
