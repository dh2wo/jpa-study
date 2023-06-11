package com.example.demo.service;

import com.example.demo.jpa.entity.MemberEntity;
import com.example.demo.jpa.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor // @Requi 치다가 엔터치면 어지간하면 얘로 찍혀요.
public class DefaultMemberService implements MemberService{
    private final MemberRepository memberRepository;

//    public DefaultMemberService(MemberRepository memberRepository) { // 파라미터로 적으면 여기에 빈이 오고
//        this.memberRepository = memberRepository; // 그걸 필드에 전달.
//    } // 이 생성자를 대신하는 게 @RequiredArgsConstructor -> 이거 쓰면 방금 만든 그 형태대로 생겨요. 넵!
    // 그럼 저희 jpa 기능이 완성 된거죠

    @Override
    public boolean signUp(MemberEntity member) {
        memberRepository.save(member);
        return true;
    }

    @Override
    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional // 트랜잭션 관리 ~~ ? (삭제 같은 거 할 때)
    public int delete(Map<String, Object> body) {
        String username = (String) body.get("username");
        return memberRepository.deleteByUsername(username);
    }

    @Override
    public List<MemberEntity> getMembersByName(String username) {
        return memberRepository.findByusername(username);
    }

//    @Override
//    public int update(Map<String, Object> body) {
//        Long id = (Long) body.get("id");
//        String nickname = (String) body.get("nickname");
//        return memberRepository.updateUserNickname(Math.toIntExact(id), nickname);
//    }
}
