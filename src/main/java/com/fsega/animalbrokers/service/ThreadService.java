package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.ThreadCreateDto;
import com.fsega.animalbrokers.model.dto.ThreadDto;
import com.fsega.animalbrokers.model.dto.ThreadSearchDto;
import com.fsega.animalbrokers.model.entity.AnimalBreed;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.entity.User;
import com.fsega.animalbrokers.model.enums.ThreadType;
import com.fsega.animalbrokers.repository.AnimalBreedRepository;
import com.fsega.animalbrokers.repository.ThreadRepository;
import com.fsega.animalbrokers.repository.UserRepository;
import com.fsega.animalbrokers.security.services.UserDetailsImpl;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.exception.NotFoundException;
import com.fsega.animalbrokers.utils.mapper.LocationMapper;
import com.fsega.animalbrokers.utils.mapper.ThreadMapper;
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
public class ThreadService {

    private final ThreadRepository threadRepo;
    private final AnimalBreedRepository animalBreedRepo;
    private final UserRepository userRepository;

    @Transactional
    public ThreadDto createThread(ThreadCreateDto threadCreateDto) {
        AnimalBreed breed = animalBreedRepo.getOne(threadCreateDto.getBreedId());
        User creator = userRepository.getOne(threadCreateDto.getCreatorId());

        Thread thread = ThreadMapper.toEntity(threadCreateDto, breed, creator);
        thread.getPhotos().forEach(photo -> photo.setThread(thread));
        return ThreadMapper.toDto(threadRepo.save(thread));
    }

    @Transactional(readOnly = true)
    public ThreadDto getThreadById(UUID threadId) {
        return ThreadMapper.toDto(threadRepo.findById(threadId)
                .orElseThrow(() -> new NotFoundException("Thread not found", ExceptionType.NOT_FOUND)));
    }

    @Transactional(readOnly = true)
    public List<ThreadDto> searchThreads(ThreadSearchDto threadSearchDto) {
        return threadRepo.searchThreads(threadSearchDto.getType(), threadSearchDto.getCreatorId())
                .stream()
                .map(ThreadMapper::toMiniDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ThreadDto updateThread(UUID threadId, ThreadCreateDto threadCreateDto) {
        Thread threadToUpdate = threadRepo.findThreadById(threadId);
        if (threadToUpdate == null) {
            throw new NotFoundException(String.format("Thread with id: %s not found.", threadId),
                    ExceptionType.NOT_FOUND);
        }
        threadToUpdate.setPhotos(toEntities(threadCreateDto.getPhotos()));
        threadToUpdate.setAnimalBreed(animalBreedRepo.getOne(threadCreateDto.getBreedId()));
        threadToUpdate.setDescription(threadCreateDto.getDescription());
        threadToUpdate.setLastKnownLocation(LocationMapper.toEntity(threadCreateDto.getLastKnownLocation()));
        threadToUpdate.setLastSeenTime(threadCreateDto.getLastSeenTime());
        threadToUpdate.setTitle(threadCreateDto.getTitle());
        threadToUpdate.setType(threadCreateDto.getType());
        return ThreadMapper.toDto(threadRepo.save(threadToUpdate));
    }

    @Transactional
    public Boolean deleteThread(UUID threadId) {
        Thread threadToDelete = threadRepo.findThreadById(threadId);
        if (threadToDelete == null) {
            throw new NotFoundException(String.format("Thread with id: %s not found.", threadId),
                    ExceptionType.NOT_FOUND);
        }

        threadRepo.delete(threadToDelete);
        return !threadRepo.existsById(threadId);
    }

    public List<ThreadType> getThreadTypes() {
        return List.of(ThreadType.values());
    }

    @Transactional(readOnly = true)
    public Boolean isLoggedInUserCreatorOfThread(UUID threadId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        UUID loggedInUserId = userDetails.getId();

        return threadRepo.existsByIdAndCreatorId(threadId, loggedInUserId);
    }
}
