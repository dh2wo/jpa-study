package com.example.demo.service;

import com.example.demo.jpa.entity.MemberEntity;
import com.example.demo.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        memberRepository.save(member); // <- 얘로 JPA 쓴 거고, 이제 컨트롤러 쪽은 기존에 하던 것대로
        return true; // 이렇게 쓰면 된다고 인텔리제이가 교정해 주는 거예요. 아하넵 근데 기훈이형이 autowired 쓰지말라고했는데 이유가 있나요?
        // 공부 안 한 티 엄청 나니까 쓰지 말라는 것도 있구요
        // final 못 줌.
        // 생성자 주입을 쓰는 게 나아요. 왜 생성자 주입을 쓰는게 더 낫나요..?
    }

    @Override
    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public int delete(String username) {
        return memberRepository.deleteByUsername(username);
    }
}
