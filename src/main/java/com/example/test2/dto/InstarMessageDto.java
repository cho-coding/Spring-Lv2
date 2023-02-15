package com.example.test2.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InstarMessageDto {
    private String message;
    private int tokencode;

    @Builder
    public void instarMessage(String message) {
        this.message = message;
        this.tokencode = tokencode;
    }
}
