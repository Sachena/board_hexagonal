package com.example.board_hexagonal.post.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostUpdatedAt {
    LocalDateTime value;
}
