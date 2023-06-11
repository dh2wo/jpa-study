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
@Builder
@Table(name = "MEMBER") // 테이블 이름도 member고, 엔티티 클래스도 member면 생략 가능.
public class MemberEntity extends BaseEntity { // 이런 식으로 하면 아이디는 부모 클래스에서 다 관리하고 그래요.
//  Table 구조 그대로 적으면 돼요.
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY) // -> Auto Increment 사용하면 이런 걸로 하면 돼요 걍.
//  public Long id; // 근데 어차피 이 부분은 앞으로 아이디 작성 안 할 거예요. 기훈 님이

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
