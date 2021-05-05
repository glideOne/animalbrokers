package com.fsega.animalbrokers.model.entity;

import lombok.*;

import javax.persistence.*;
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

    private List<Photo> photos;

}
