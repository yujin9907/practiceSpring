package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;    // 회원 서비스
    MemoryMemberRepository memberRepository;    // 메모리 회원 리퍼지토리

    // 테스트가 끝날 때마다 메모리 비우기
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
        //given ; 회원 생성
        Member member = new Member();
        member.setName("yujin");

        //when ; 서비스의 조인 검증, 맴버 객체를 넣어보고, 아이디가 잘 나와야 됨
        Long saveId = memberService.join(member);

        //then 검증은 어썰트로
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() throws Exception{
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}