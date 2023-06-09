package com.example.demo.jpa.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

// 이 베이스 엔티티는 뭐하는 친구죠? 그리고 저희db.migration에서 AUTO_INCREMENT를 등록해줬는데 여기서 또 써주는건가요? 네. 아이디는 필요하니까요
@Getter // 걍 롬복
@EqualsAndHashCode // 걍 롬복
@MappedSuperclass // <- 엔티티에 상속시키려면 이거 넣는 거고
public abstract class BaseEntity implements Serializable { // 이건 나중에 다른 패키지에 기훈 님이 넣어 놨을 건데
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}