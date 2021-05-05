package com.fsega.animalbrokers.model.entity;

import com.fsega.animalbrokers.model.enums.ThreadType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ThreadType type;

    @Column(nullable = false)
    private AnimalClass animalClass;

    @Column(nullable = false)
    private AnimalBreed animalBreed;

    private List<Photo> photos;
    private Location lastKnownLocation;

    private LocalDateTime lastSeenTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "thread")
    private List<Post> posts;

}
