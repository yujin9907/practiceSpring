package repository;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.PortUnreachableException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트가 끝날 때마다 실행
    @AfterEach
    public void afterEach(){
        repository.clearStore(); // 메모리에 있는 모든 member 삭제함 (테스트 순서에 의존관계 없
    // 메모리에 member 저장이 잘 되는지 검수 함수
    @Test
    public void save(){
        Member member = new Member(); // name이 spring인 멤버가 있을 때
        member.setName("spring");

        repository.save(member); // member 메모리에 저장

        Member result = repository.findById(member.getId()).get(); // optional 안 멤버 객체 반환

        assertThat(result).isEqualTo(member); // 메모리에서 가져온 멤버가 생성한 멤버와 같은지 검증
    }

    // 저장된 데이터가 2개일 때 테스트
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    //모든 객체가 제대로 조회되는지
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

