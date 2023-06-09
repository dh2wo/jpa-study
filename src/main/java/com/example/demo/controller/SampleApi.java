package com.example.demo.controller;

import com.example.demo.controller.dto.SampleDto.SampleRequestDto;
import com.example.demo.controller.dto.SampleDto.SampleResponseDto;
import com.example.demo.jpa.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // No Page. Only API
@RequiredArgsConstructor
public final class SampleApi {
    // API(uses DTO) -> Service -> Repository(uses Entity)    // < - 이거 복습하시는 거예요. 흐름만 잘 기억하면 진짜 쉬워용.
    // 이게 기본 흐름이에요. API가 컨트롤러.

    private final MemberService memberService; // use `interface` 타입.
    // 이제 이게 에이피아이 골격이 될 거예요. 보면 받는 게 있고(리퀘스트 디티오 등), 응답하는 게 있는.
    @PostMapping("/sample")
    public SampleResponseDto sample(@RequestBody @Valid SampleRequestDto body) {
        // @Valid: 아까 적은 것들 작동하게 해 주는 거.
        // @RequestBody: 포스트로 쏘면 바디를 담을 수 있으니까, 저기 적은 게 파람이 아니라 바디라고 체크한 것.

        // 이제 응답하기 전에 여기서 서비스 써서 모든 걸 하면 돼요.
        MemberEntity member = MemberEntity.builder()
                .username(body.username())
                .password(body.password()) // raw password
                .email(body.email())
                .nickname(body.nickname())
                // .roleName(RoleType.ROLE_USER) // @Builder.Default 줬으니까 생략 가능
                .build();
        memberService.signUp(member);

        // 비밀번호 인코딩 빼고 완성.
        // 저번에 집에서 보여주신게 postman으로 jwt까지 써서 보여주신거죠..?
        // 네, 이따가 저녁에 더 가능하긴 한데 어떡하실래요?
        // 도커 데스크톱 다시 설치하고 나서 오시면 더 가능해요.
        // 넵 저 10시..쯤 가능할것같아요 네, 10시에 몇몇 분들 해서 기능 하나만 정해서 만들어 보고
        // 그 전에는 하원하기 전까지 복습해 보셔요.
        // 넵 오늘한거 정리하고 그전에 이거 signup만든거 구동되는거 보여주실수 있나요?
        // 노 도커 노 구동
        // 노 기훈 헉... 알겠습니다! 고생하셨어요!! 기훈이형한테 많이 물어볼게요!
        // 넵!! 오늘 정말 고생하셨습니다! 질문 계속 받아주셔서 이해가 더 편했어요 네!! 원격 끌게요 파이팅
        // 그저 압도적 감사..!
        // 생성자 대신에 이렇게 빌더라는 걸 쓰는 거예요. 빌더 패턴 검색하면 목적은 나와요.
        // 근데 우리 같은 경우는 그 검색해서 나오는 목적(생성자에 넣는 순서 불편) 말고도
        // 응답 데이터 형태 바뀔 때마다 생성자 쓰이는 모든 곳 다 바꿔야 하는 불편 지우려는 게 있는데, 나중에 다시 설명할게요. 물어보면
        return SampleResponseDto.builder()
                .success(true)
                .build();
    }

    @GetMapping("/users")
    public List<MemberEntity> retrieveAllUsers(){
        List<MemberEntity> members = memberService.findAll();
        return members;
    }

    @GetMapping("/delete")
    public int member(String username){
        return memberService.delete(username);
    }
}
