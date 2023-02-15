package com.example.test2.controller;


import com.example.test2.dto.InstarMessageDto;
import com.example.test2.dto.InstarRequestDto;
import com.example.test2.dto.InstarResponseDto;
import com.example.test2.entity.Instar;
import com.example.test2.service.InstarService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InstarController {

    private final InstarService instarService;


    //게시글 생성 POST
    @PostMapping("/api/instars")
    public InstarResponseDto createInstar(@RequestBody InstarRequestDto instarRequestDto, HttpServletRequest request) {
        return instarService.createInstar(instarRequestDto, request);
    }

    //게시글 전체 조회 GET
    @GetMapping("/api/instars")
    public List<InstarResponseDto> getInstars() {

        return instarService.getInstars();
    }


    //게시글 일부 조회 GET
    @GetMapping("/api/instars/{id}")
    public InstarResponseDto getInstars(@PathVariable Long id) {
        return instarService.getInstarsId(id);
    }


    //게시글 수정 PUT
    @PutMapping("/api/instars/{id}")
    public InstarResponseDto updateInstar(@PathVariable Long id, @RequestBody InstarRequestDto instarRequestDto, HttpServletRequest request) {
        return instarService.update(id, instarRequestDto, request);
    }

    //게시글 삭제 DELETE
    @DeleteMapping("/api/instars/{id}")
    public InstarMessageDto deleteInstar(@PathVariable Long id, InstarRequestDto requestDto,HttpServletRequest request) {
        return instarService.deleteInstar(id, requestDto, request);
    }
}
