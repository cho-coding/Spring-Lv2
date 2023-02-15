package com.example.test2.service;

import com.example.test2.dto.*;
import com.example.test2.entity.Instar;
import com.example.test2.entity.User;
import com.example.test2.jwt.JwtUtil;
import com.example.test2.repository.InstarRepository;
import com.example.test2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstarService {

    private final InstarRepository instarRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    //게시글 전체 조회
    @Transactional
    public List<InstarResponseDto> getInstars() {
        List<Instar> instarList = instarRepository.findAllByOrderByModifiedAtDesc();
        List<InstarResponseDto> instarResponseDtoList = new ArrayList<>();

        //게시판 조회 내림차순
        for (Instar instar : instarList) {
            InstarResponseDto tmp = new InstarResponseDto(instar);
            instarResponseDtoList.add(tmp);
        }
        return instarResponseDtoList;
    }


    //선택한 게시글 조회
    @Transactional(readOnly = true)
    public InstarResponseDto getInstarsId(Long id) {
        Instar instar = instarRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다."));
        InstarResponseDto instarResponseDto = new InstarResponseDto(instar);
        return instarResponseDto;
    }

    //회원인 경우 게시글 생성
    @Transactional
    public InstarResponseDto createInstar(InstarRequestDto instarRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시물 작성 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.valiadteToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
//            return InstarResponseDto(instarRepository.saveAndFlush(Instar.builder()
//
//            ))
            Instar instar = instarRepository.saveAndFlush(new Instar(instarRequestDto, user));
            InstarResponseDto instarResponseDto = new InstarResponseDto(instar);
            return instarResponseDto;
        } else {
            return null;

        }
    }


    //회원 게시글 수정하기
    @Transactional
    public InstarResponseDto update(Long id, InstarRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        Instar instar = new Instar();

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.valiadteToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            instar = instarRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
            );

            instar.update(requestDto, user);
            return new InstarResponseDto(instar);
        }
        return null;
    }

    //회원 게시글 삭제
    @Transactional
    public InstarMessageDto deleteInstar(Long id, InstarRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.valiadteToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Instar instar = instarRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

            InstarMessageDto instarMessageDto = new InstarMessageDto();
            if (instar != null) {
                instarRepository.delete(instar);
                instarMessageDto.instarMessage("삭제완료!");

                return instarMessageDto;
            } else {
                instarMessageDto.instarMessage("삭제실패!");
            }
            return instarMessageDto;
        }
        return null;
    }
}