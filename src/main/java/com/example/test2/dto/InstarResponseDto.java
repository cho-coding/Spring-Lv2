package com.example.test2.dto;

import com.example.test2.entity.Instar;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class InstarResponseDto {
    private Long id;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public InstarResponseDto(Instar instar) {
        this.id = instar.getId();
        this.contents = instar.getContents();
        this.title = instar.getTitle();
        this.createdAt = instar.getCreatedAt();
        this.modifiedAt = instar.getModifiedAt();
    }
}

/*
##조회하기##

제목(contents)
작성자명(username)
작성내용(contents)
작성날짜(createdAt)

 */
