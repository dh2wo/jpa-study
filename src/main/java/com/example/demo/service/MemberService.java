package com.example.demo.service;

import com.example.demo.jpa.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    // spec은 원하는 대로 작성하면 돼요. 여기부턴 그냥 원래 알던 그 서비스.
    boolean signUp(MemberEntity member);

    List<MemberEntity> findAll();

    int delete(String username);
}
