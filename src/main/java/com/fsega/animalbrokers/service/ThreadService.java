package com.fsega.animalbrokers.service;

import com.fsega.animalbrokers.model.dto.ThreadCreateDto;
import com.fsega.animalbrokers.model.dto.ThreadDto;
import com.fsega.animalbrokers.model.entity.Thread;
import com.fsega.animalbrokers.model.enums.ThreadType;
import com.fsega.animalbrokers.repository.ThreadRepository;
import com.fsega.animalbrokers.utils.exception.ExceptionType;
import com.fsega.animalbrokers.utils.exception.NotFoundException;
import com.fsega.animalbrokers.utils.mapper.ThreadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ThreadService {

    private final ThreadRepository threadRepo;

    @Transactional
    public ThreadDto createThread(ThreadCreateDto threadCreateDto) {
        Thread thread = ThreadMapper.toEntity(threadCreateDto);
        return ThreadMapper.toDto(threadRepo.save(thread));
    }

    @Transactional(readOnly = true)
    public List<ThreadDto> getThreads(ThreadType type) {
        if (type == null) {
            return threadRepo.findAll().stream()
                    .map(ThreadMapper::toDto)
                    .collect(Collectors.toList());
        }
        return threadRepo.getAllByType(type).stream()
                .map(ThreadMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ThreadDto getThreadById(UUID threadId) {
        return ThreadMapper.toDto(threadRepo.findById(threadId)
                .orElse(null));
    }

    @Transactional(readOnly = true)
    public List<ThreadDto> getThreadsByUserId(UUID userId) {
        return threadRepo.getAllByCreator(userId).stream()
                .map(ThreadMapper::toDto)
                .collect(Collectors.toList());
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

}
