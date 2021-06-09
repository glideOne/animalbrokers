package com.fsega.animalbrokers.controller;

import com.fsega.animalbrokers.model.dto.*;
import com.fsega.animalbrokers.model.entity.Photo;
import com.fsega.animalbrokers.model.enums.ThreadType;
import com.fsega.animalbrokers.repository.PhotoRepository;
import com.fsega.animalbrokers.service.ThreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.fsega.animalbrokers.utils.Constants.API_VERSION;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = API_VERSION + "/threads")
public class ThreadController {

    private final ThreadService threadService;
    private final PhotoRepository photoRepo;

    @PostMapping
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

    @PostMapping("/photo")
    public Boolean uploadPhoto(@RequestHeader(name = "Authorization") String token,
                               @RequestBody byte[] file) {
//        try {
//            byte[] image = file.getBytes();
            Photo photo = Photo.builder()
                    .image(file)
                    .build();

            photoRepo.save(photo);



//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return true;
    }

}
