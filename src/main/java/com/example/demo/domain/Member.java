package com.example.demo.domain;

public class Member {
    // 회원 객체 = Member
    private Long id; //실제 id 가 아닌 데이터 구분을 위해
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
