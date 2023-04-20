package com.example.board_hexagonal.user.adapter.out;


import com.example.board_hexagonal.post.adapter.out.PostEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "posts")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime joinedAt;

    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts = new ArrayList<>();

}
