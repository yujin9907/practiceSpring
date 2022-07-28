package com.example.demo.repository;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 객체 저장
    Optional<Member> findById(Long id); // 회원 id 조회
    Optional<Member> findByName(String name); // 회원 이름 조회
    List<Member> findAll(); // 저장된 모든 회원 리스트 조회
}
