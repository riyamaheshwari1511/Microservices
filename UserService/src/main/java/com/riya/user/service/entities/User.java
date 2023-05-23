package com.riya.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "micro_users")
public class User {
    @Id
    private String userId;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String about;

    @Transient
    List<Rating> rating = new ArrayList<>();


}
