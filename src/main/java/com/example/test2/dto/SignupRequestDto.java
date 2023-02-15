package com.example.test2.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    
    @NotNull
    @Pattern(regexp = "[a-z0-9]+@[a-z]+[.]+[a-z.]+")
    @Size(min=3, max=9)
    private String username;

    @NotNull
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+")
    @Size(min=7, max=14)
    private String password;
}
