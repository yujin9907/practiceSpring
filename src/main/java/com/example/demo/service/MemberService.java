package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 이건 그냥 순수한 자바 파일이기 때문에
// 스프링이 이년을 인식하기 위해서 어노테이션 서비스를 붙임

public class MemberService {
    // 메모리 회원 레퍼지토리 직접 생성
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 스프링이 서비스를 인식하고 등록할 때
    // 오토와이어드로 자동으로 생성자를 등록하게 함
    // 근데 멤버 레포지토리 필요하네?
    // 바로 컨테이너에 메모리멤버를 주입을 해줌.
    // 그럼 세갠가 네개가 다 연결됨

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        // 중복 이름 ㄴㄴ
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //파라미터로 들어온 객체가 메모리에 이미 존재하는지 검증, 중복처리 따로 메서드로 빼준거
    private void validateDuplicateMember(Member member) {
        // optional 안 값이 있으면(=널값이 아니면) 예외 처리
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    // 해당 아이디에 해당하는 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
