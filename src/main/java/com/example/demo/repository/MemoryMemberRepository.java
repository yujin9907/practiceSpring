package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 이것도 스프링이 인식하기 위해서 어노테이션 리포지토리를 붙여줌

public class MemoryMemberRepository implements MemberRepository{
    // 클래스가 인터페이스를 상속할 땐 임플리맨츠만 사용 가능
    // Map<key: member의 id, value: member>
    private static Map<Long, Member> store = new HashMap<>(); // 회원 저장 공간
    private static long sequence = 0L; // 시스템에서 MEMBER의 ID를 자동을 넣어주기 위함
                                        //참고로 저 0(제로)L임
    // (ID, MEMBER)를 메모리에 저장하고 MEMBER를 반환함
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 값을 자동으로 1개 증가
        store.put(member.getId(), member); //(member의 id, member)를 map에 넣음
        return member;
    }

    // key가 id인 member 를 반환하는 함수
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // member가 null 일 수 있으니 optional로 감싸기
    }

    // 메모리에 저장된 모든 member(value)들을 반환하는 함수
    @Override
    public List<Member> findAll(){
        return new ArrayList<>(store.values()); // store의 member 반환
    }

    // 메모리에 저장된 회원 중 파라미터(name)과 같은 member의 name을 가진 member를 반환하는 함수
    @Override
    public Optional<Member> findByName(String name) {
        // 데이터소스 객체 집합. steam 생성. 중개연산, 최종연산
        // steam에서 filter(조건)을 만족하는 어떤 요소 반환
        return store.values().stream()
                .filter(member ->member.getName().equals(name))
                .findAny();
    }

    // 메모리에 저장된 모든 회원 정보(ID, MEMBER)를 삭제하는 함수
    public void clearStore(){
        store.clear();
    }

}
