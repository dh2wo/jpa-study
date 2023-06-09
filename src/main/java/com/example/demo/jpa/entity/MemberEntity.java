package com.example.demo.jpa.entity;

import com.example.demo.jpa.entity.type.RoleType;
import com.example.demo.util.time.ServerTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

// 롬복 빼고 보면 어노테이션 두 개밖에 없어요
// 롬복은 그냥 클래스 쉽게 구성하려고 넣는 거니까 엔티티 이해에는 불필요해요.
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder // IDE가 인식을 못 하는 듯. 어쨌든 파일엔 에러 안 뜨니까 넘어갈게요. 넵 ide가 바보네..
@Table(name = "user") // 테이블 이름도 member고, 엔티티 클래스도 member면 생략 가능.
public class MemberEntity extends BaseEntity { // 이런 식으로 하면 아이디는 부모 클래스에서 다 관리하고 그래요.
    // 여기서 말씀해주시는 아이디가 멤버아이디나 보드 아이디나 이런거 말씀이신가여..? 네
    // 회원 계정 말고, 회원 번호 같은 거요. 일련번호로 지급되는.아.. 배열 인덱스처럼요? 네.

    // 디비 인덱스는 다른 개념이라서 혼용하면 안 되고(<- 조회 성능 높이기 위한 것: 인덱스 부여한 컬럼은 데이터를 매번 정렬해 놓고 이진탐색에 쓰니까.)
    // 그건 따로 공부요!

    // ORM 쓰면 원래 아이디 컬럼 같은 걸 자동으로 부여하는 것들도 있어요. 그래서 어지간하면 우리도 만들 때 아이디 두는 게, 나중에 기술 바꾸더라도
    // 일관성 있게 유지할 수 있어서 아이디 컬럼은 두는 편이에요.

    // Table 구조 그대로 적으면 돼요.
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // -> Auto Increment 사용하면 이런 걸로 하면 돼요 걍.
//    public Long id; // 근데 어차피 이 부분은 앞으로 아이디 작성 안 할 거예요. 기훈 님이
    public String username;
    public String password;
    public String nickname;
    public String email;

    // Default Mapping: snake_case to camelCase
    @Builder.Default // 방금 드래그한 부분이 안 작성됐다고 생각하는 듯.
    public OffsetDateTime regDate = ServerTime.now(); // Default Value 그냥 대입해 놓으면 돼요. 빌더에서도 이 값 쓰려면 빌더.디폴트까지.

    @Enumerated(EnumType.STRING) // Default가 ORDINARY라서 이거는 달아 줘야 해요. 열거형만.
    @Builder.Default
    public RoleType roleName = RoleType.ROLE_USER;
}

// 이러면 엔티티 완성. 그냥 테이블 받아쓰기죠.

// 엔티티는 그냥 받아쓰기라서 쉽죠? 네!
// 아 디폴트 알려 줄게요 으윽...어려워요...

// 서버 타임 유틸은 그냥 유틸이니까 만드는 거 신경쓰지 마시고
// 그냥 현재 시간 가져오는 걸 넣어 놓은 거예요.여기서 임포트 안하고 나눠서 하는 이유가 있나요? private랑 public도 헷갈려요 개념은 아는데