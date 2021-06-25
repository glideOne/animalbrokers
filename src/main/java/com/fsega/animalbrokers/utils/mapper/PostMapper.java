package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.PostCreateDto;
import com.fsega.animalbrokers.model.dto.PostDto;
import com.fsega.animalbrokers.model.entity.Post;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.entity.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static com.fsega.animalbrokers.utils.mapper.PhotoMapper.toDtos;
import static com.fsega.animalbrokers.utils.mapper.PhotoMapper.toEntities;

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
                .photos(toEntities(postCreateDto.getPhotos()))
                .spottedAt(LocationMapper.toEntity(postCreateDto.getSpottedAt()))
                .build();
    }

    public static PostDto toDto(Post post) {
        if (post == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.of("Europe/Bucharest");
        ZoneOffset currentOffsetForMyZone = zoneId.getRules().getOffset(post.getCreated());
        return PostDto.builder()
                .id(post.getId())
                .text(post.getText())
                .created(LocalDateTime.ofInstant(post.getCreated(), currentOffsetForMyZone))
                .photos(toDtos(post.getPhotos()))
                .spottedAt(LocationMapper.toDto(post.getSpottedAt()))
                .poster(UserMapper.toDto(post.getPoster()))
                .build();
    }

}
