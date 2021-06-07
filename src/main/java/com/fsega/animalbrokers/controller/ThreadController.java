package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.*;
import com.fsega.animalbrokers.model.enums.ThreadType;
import com.fsega.animalbrokers.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/threads")
public class ThreadController {

    private final ThreadService threadService;

    @PostMapping
    public ThreadDto createThread(@RequestBody @Valid ThreadCreateDto threadCreateDto) {
        return threadService.createThread(threadCreateDto);
    }

    @GetMapping("/{threadId}")
    public ThreadDto getThread(@PathVariable UUID threadId) {
        return threadService.getThreadById(threadId);
    }

    @GetMapping
    public List<ThreadDto> searchThreads(@ModelAttribute ThreadSearchDto threadSearchDto) {
        return threadService.searchThreads(threadSearchDto);
    }

    @PutMapping("/{threadId}")
    public ThreadDto updateThread(@PathVariable UUID threadId, @RequestBody @Valid ThreadCreateDto threadCreateDto) {
        return threadService.updateThread(threadId, threadCreateDto);
    }

    @DeleteMapping("/{threadId}")
    public Boolean deleteThread(@PathVariable UUID threadId) {
        return threadService.deleteThread(threadId);
    }

    @GetMapping("/types")
    public List<ThreadType> getThreadTypes(@RequestHeader(name = "Authorization") String token) {
        return threadService.getThreadTypes();
    }

}
