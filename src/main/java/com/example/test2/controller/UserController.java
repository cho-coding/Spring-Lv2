package com.example.test2.controller;

import com.example.test2.dto.InstarResponseDto;
import com.example.test2.dto.LoginRequestDto;
import com.example.test2.dto.SignupRequestDto;
import com.example.test2.entity.User;
import com.example.test2.service.UserService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    // DI주입
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    @ResponseBody
    public String signup(@RequestBody SignupRequestDto signupRequestDto, @Valid BindingResult bindingResult) {
        return userService.signup(signupRequestDto,bindingResult);
    }

    // 로그인
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}