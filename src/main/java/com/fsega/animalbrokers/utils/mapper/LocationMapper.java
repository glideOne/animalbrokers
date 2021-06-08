package com.fsega.animalbrokers.utils.mapper;

import com.fsega.animalbrokers.model.dto.LocationDto;
import com.fsega.animalbrokers.model.entity.Location;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationMapper {

    public static Location toEntity(LocationDto locationDto) {
        if (locationDto == null) {
            return null;
        }
        return Location.builder()
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .build();
    }

    public static LocationDto toDto(Location location) {
        if (location == null) {
            return null;
        }
        return LocationDto.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

}
