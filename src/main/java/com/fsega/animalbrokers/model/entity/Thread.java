package com.fsega.animalbrokers.model.entity;

import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thread extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ThreadType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id", nullable = false)
    private AnimalBreed animalBreed;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    private LocalDateTime lastSeenTime;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "location_id")
    private Location lastKnownLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public List<Post> getPosts() {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        return posts;
    }

    public List<Photo> getPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        return photos;
    }
}
