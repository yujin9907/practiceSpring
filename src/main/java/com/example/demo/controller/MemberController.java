package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller // 자바코드로 직접 빈 등록할 때도 컴포넌트 스캔으로만 올라감. 어차피 오토와이어드니까 뭐 해줄 건 없음
// 어노테이션이 있으면 컨트룰러 객체를 생성해서 스프링에 넣어둠(메모리에 올림) -> 스프링이 관리
// 즉, 어노테이션을 통해 컨트룰러 객체를 생성하고
// = 스프링 빈을 통해 스프링 컨테이너에서 관리를 해줌
public class MemberController {
    // 멤버 서비스 필요함
//    private final MemberService memberService = new MemberService();
    // 컨테이너가 관리하게 되면 전부 컨트룰러가 관리할 수 있게 바꿔서 써야 됨
    // 즉, 위와 같이 new로 객체를 생성하면 이 컨트룰러 말고 다른 컨트룰러도 가져다 슬 수 있음
    // 근데 별 기능이 없어서 여러개 생성할 필요 없이 하나만 생성하면 됨
    // 스프링 컨테이너한테 등록해주고 사용
    private final MemberService memberService;

    // 컨테이너에 뜰 때 생성자를 호출해주는데, 스프링 컨테이너와 멤버 서비스를 연결을 시켜줌
    // 즉 오토와이어드 어노테이션을 통해서 멤버서비스를 스프링 컨테이너에서 가져오는 것
    // 의존관계주입, 디펜던시 인젝션임.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 이제 그 받아온 키벨류값으로 회원 생성
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member); // 서비스에 저장

        return "redirect:/"; // 첫 창으로 돌아가는 그 리다이렉트 맞음
    }

    @GetMapping("/members") // 조회니까 get
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
