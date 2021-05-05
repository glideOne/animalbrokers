package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.PostDto;
import com.fsega.animalbrokers.model.entity.Post;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostMapper {

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
