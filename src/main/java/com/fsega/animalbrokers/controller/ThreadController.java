package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.*;
import com.fsega.animalbrokers.model.enums.ThreadType;
import com.fsega.animalbrokers.service.ThreadService;
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
@RequestMapping(value = API_VERSION + "/threads")
public class ThreadController {

    private final ThreadService threadService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("@userService.isLoggedInUserActive()")
    public ThreadDto createThread(@RequestHeader(name = "Authorization") String token,
                                  @RequestBody @Valid ThreadCreateDto threadCreateDto) {
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
    @PreAuthorize("@threadService.isLoggedInUserCreatorOfThread(#threadId) and " +
            "@userService.isLoggedInUserActive()")
    public ThreadDto updateThread(@PathVariable UUID threadId, @RequestBody @Valid ThreadCreateDto threadCreateDto) {
        return threadService.updateThread(threadId, threadCreateDto);
    }

    @DeleteMapping("/{threadId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or " +
            "(@threadService.isLoggedInUserCreatorOfThread(#threadId) and " +
            "@userService.isLoggedInUserActive())")
    public Boolean deleteThread(@RequestHeader(name = "Authorization") String token,
                                @PathVariable UUID threadId) {
        return threadService.deleteThread(threadId);
    }

    @GetMapping("/types")
    public List<ThreadType> getThreadTypes(@RequestHeader(name = "Authorization") String token) {
        return threadService.getThreadTypes();
    }

}
