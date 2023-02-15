package com.example.test2.entity;

import com.example.test2.dto.InstarRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Instar extends Timestamped {

    @ManyToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Builder
    public Instar(InstarRequestDto requestDto,User user) {
        this.contents = requestDto.getContents();
        this.user = user;
    }

    public void update(InstarRequestDto instarRequestDto,User user) {
        this.contents = instarRequestDto.getContents();
        this.user = user;


    }
}