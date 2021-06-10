package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "thread_id")
    private Thread thread;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poster_id", nullable = false)
    private User poster;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location spottedAt;

    public List<Photo> getPhotos() {
        if (photos == null) {
            photos = new ArrayList<>();
        }
        return photos;
    }

}
