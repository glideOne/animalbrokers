package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.PhotoDto;
import com.fsega.animalbrokers.model.entity.Photo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PhotoMapper {

    public static List<Photo> toEntities(List<PhotoDto> photoDtos) {
        if (photoDtos == null) {
            return null;
        }
        return photoDtos.stream()
                .map(PhotoMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<PhotoDto> toDtos(List<Photo> photos) {
        if (photos == null) {
            return null;
        }
        return photos.stream()
                .map(PhotoMapper::toDto)
                .collect(Collectors.toList());
    }

    private static Photo toEntity(PhotoDto dto) {
        if (dto == null) {
            return null;
        }
        return Photo.builder()
                .image(dto.getImage())
                .build();
    }

    private static PhotoDto toDto(Photo photo) {
        if (photo == null) {
            return null;
        }
        return PhotoDto.builder()
                .image(photo.getImage())
                .build();
    }

}
