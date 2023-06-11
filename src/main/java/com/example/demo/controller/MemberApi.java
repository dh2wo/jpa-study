package com.example.demo.controller;

import com.example.demo.controller.dto.MemberDto.SignupRequestDto;
import com.example.demo.controller.dto.MemberDto.SignupResponseDto;
import com.example.demo.jpa.entity.MemberEntity;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // No Page. Only API
@RequiredArgsConstructor
public final class MemberApi {
    // API(uses DTO) -> Service -> Repository(uses Entity)

    private final MemberService memberService;
    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody @Valid SignupRequestDto body) {
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
        return SignupResponseDto.builder()
                .success(true)
                .build();
    }

    @GetMapping("/users")
    public List<MemberEntity> retrieveAllUsers(){
        List<MemberEntity> members = memberService.findAll();
        return members;
    }

    @GetMapping("/selectby")
    public List<MemberEntity> getMembersByName(@RequestBody body) {
        System.out.println(username);
        return memberService.getMembersByName(username);
    }

    @GetMapping("/delete")
    public String delete(@RequestBody Map<String, Object> body){
        int result = memberService.delete(body);
        if (result == 1) {
            return "OK";
        }
        return "ERROR";
    }

//    @GetMapping("/update")
//    public int update(@RequestBody Map<String, Object> body){
//        return memberService.update(body);
//    }


}
