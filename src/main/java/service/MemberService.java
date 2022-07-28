package service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {
    // 메모리 회원 레퍼지토리 직접 생성
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 중복 이름 ㄴㄴ
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //파라미터로 들어온 객체가 메모리에 이미 존재하는지 검증
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
