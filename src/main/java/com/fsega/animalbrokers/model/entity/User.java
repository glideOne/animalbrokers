package com.fsega.animalbrokers.model.entity;

import com.fsega.animalbrokers.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ab_user")
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean active;

    private byte[] avatar;

    @OneToMany(mappedBy = "creator")
    private List<Thread> threads;

    @OneToMany(mappedBy = "poster")
    private List<Post> posts;

}
