package com.example.test2.repository;

import com.example.test2.entity.Instar;
import com.example.test2.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstarRepository extends JpaRepository<Instar, Long> {
    List<Instar> findAllByOrderByModifiedAtDesc();
}

/*
###작성날짜 기준으로 내림차순으로 정렬하기###
findAll (다 찾아서)
ByOrderBy (이 기준으로 정렬해주고)
ModifiedAt (ModifiedAt이라는 멤버 변수로)
Desc (내림차순해줘)
*/