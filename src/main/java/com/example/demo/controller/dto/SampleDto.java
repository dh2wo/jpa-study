package com.example.demo.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public record SampleDto() {
    // inner class로 작성하면 돼요.
    // 하나하나 다 파일로 작성하면 파일이 너무 많아져서
    // 이렇게 탑레벨 클래스 하나 두고(SampleDto)
    // 실제 사용할 클래스들은 전부 내부 클래스로 작성

    // Client - (Request DTO) -> Server
    // Client <- (Response DTO) - Server
    // OK? ok!
    public record SampleRequestDto(
            // Spring Starter Validation
            @NotBlank // for String: not null && not ""(blank) 와... 그럼 이거 하나로 db만들때 not null 안줘도 되는거에요?
                    // DTO는 엔티티가 아니니까 디비에 직접 영향은 아니어도
                    // 유효성을 이렇게 서버 프로그램 쪽에서 관리하니까 디비에 제약조건 안 줘도 다 관리가 돼요
            @Pattern(
                    // 정규식 알아요? 넵 regexp로 저희도 한번 썼어요 근데 자바스크립트에서 썼어서..
                    // 프론트에서 한 거잖아요? -> 그러면 그냥 개발자도구 켜서 ajax 쏘기만 해도 그 유효성은 건너뛸 수 있죠.
                    // 그래서 유효성은: 프론트에서 -> 사용자 편의 때문에 제공하는 것. 서버에서 -> 보안이나 데이터 무결성 위해서. 와..
                    // 저도 저게 고민이었거든요 개발자 모드만 켜서 보면 다 처리할수 있길래 아침에도 팀 회의할때 말했었거든요..
                    // 굿... 보안을 보는 관점이 어느 정도들 있네요 신기하네요. 미리 알아채다니
                    regexp = "^[a-zA-Z0-9]{2,}", // 데모니까 암거나 막 할게요.
                    message = "계정은 영문과 숫자로 2글자 이상 어쩌고 저쩌고"
            )
            String username,

            @NotBlank
            @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password should only contain letters and numbers.")
            String password,

            @NotBlank
            @Pattern(regexp = "[a-zA-Z]+", message = "Nickname should only contain letters.")
            String nickname,

            @NotBlank
            @Email(message = "Invalid email format.")
            String email

    ) {}

    // Request DTO 작성은 간단하죠. 사용자가 입력한 데이터에 대해서 저렇게 유효성 넣어 주면 끝이에요.넵! 근데 질문 있습니다!
    // @Pattern 안에 있는 message는 정규식에서 걸리면 실행되는 건가요? 네, 400 BAD_REQUEST 응답하면서 저 메시지를 담아 줘요

    // 응답 디티오는 응답할 때 쓰니까, 우리가 서버 코드에서 객체를 만들어야 하는데
    // 레코드가 불변객체이기도 하고, 응답 스펙 바뀔 때마다 생성자 다시 쓰기 귀찮거든요.
    // 그래서 빌더 권해요 -> 순서 상관 없이 변수명으로 생성하는 그거.

    @Builder // 이거 위에 롬복에서 임포트 한건가요? 네, 롬복이 이렇게 뭔가 여러 가지 지원해요 넵!!
    public record SampleResponseDto(
            Boolean success // 이번 예시는 최대한 심플하게 할게요.
    ) {}
}
