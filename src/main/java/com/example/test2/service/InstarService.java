package com.example.test2.service;

import com.example.test2.dto.*;
import com.example.test2.entity.Instar;
import com.example.test2.repository.InstarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class InstarService {

    private final InstarRepository instarRepository;



    //생성자(Constructor) 방식
    @Autowired
    public InstarService(InstarRepository instarRepository) {
        this.instarRepository = instarRepository;
    }

    //게시글 생성
    public InstarResponseDto createInstar(InstarRequestDto requestDto, HttpServletRequest request) {
        Instar instar = new Instar(requestDto);
        Instar savedPost = instarRepository.save(instar);
        InstarResponseDto instarResponseDto = new InstarResponseDto(savedPost);
        return instarResponseDto;
    }

    //게시글 전체 조회
    //https://melonicedlatte.com/2022/02/19/124300.html
    public List<Instar> getInstars() {
        return instarRepository.findAllByOrderByModifiedAtDesc();
    }


    //선택한 게시글 조회
    @Transactional(readOnly = true)
    public InstarResponseDto getInstarId(Long id) {
        Instar instar = instarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다." + id));
        Instar savedPost = instarRepository.save(instar);
        InstarResponseDto instarResponseDto = new InstarResponseDto(savedPost);
        return instarResponseDto;
    }

    //게시글 수정하기
    @Transactional(readOnly = true)
    public InstarResponseDto update(Long id, InstarRequestDto requestDto, HttpServletRequest request) {
        Instar instar = instarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (requestDto.getPassword().equals(instar.getPassword())) {
            instar.update(requestDto);
        }
        InstarResponseDto instarResponseDto = new InstarResponseDto(instar);
        return instarResponseDto;
    }

    //게시글 삭제
    @Transactional
    public InstarMessageDto deleteInstar(Long id, InstarRequestDto requestDto, HttpServletRequest request) {
        Instar instar = instarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        InstarMessageDto instarMessageDto = new InstarMessageDto();
        if (requestDto.getPassword().equals(instar.getPassword())) {
            instarRepository.deleteById(id);
            instarMessageDto.instarMessage("삭제완료!");
            return instarMessageDto;
        } else {
            instarMessageDto.instarMessage("삭제실패!");
        }
        return instarMessageDto;
    }



}