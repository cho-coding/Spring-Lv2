package com.example.test2.entity;

import com.example.test2.dto.InstarRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Instar extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    public Instar(String username, String contents) {
        this.username = username;
        this.contents = contents;
        this.password = password;
    }

    public Instar(InstarRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(InstarRequestDto instarRequestDto) {
        this.username = instarRequestDto.getUsername();
        this.contents = instarRequestDto.getContents();
        this.password = instarRequestDto.getPassword();
    }
}