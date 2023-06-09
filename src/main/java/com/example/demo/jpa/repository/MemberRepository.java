package com.example.demo.jpa.repository;

import com.example.demo.jpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Entity는 그냥 저렇게 작성하면 돼서 쉽고 JPA 레포지터리가 실제 JPA 사용하기 위한 곳인데
// 우린 아무것도 안 할 거라서 인터페이스로 만들어요
public interface MemberRepository extends JpaRepository<MemberEntity, Long> { // Generic: <ENTITY, ID>
    // 아무것도 안 해도 기본적인 건 있어요.
    // 컨트롤+아이 눌러서 뜬 방금 목록은 그냥 쓸 수 있는 목록이고
    MemberEntity findByUsername(String username);
    List<MemberEntity> findByUsernameAndEmail(String username, String email); // 이렇게 그냥 인텔리제이가 잘 알긴 해요.

    boolean existsByEmail(String email); // 이미 존재하는 이메일입니다.
    boolean existsByUsernameOrEmail(String username, String email); // 이미 존재하는 계정 또는 이메일입니다.
    // 이렇게 쓰기만 해도 다 되구요

    @Query("select mem from MemberEntity mem where mem.username = ?1") // 이거도 아이디이 문제인 듯? 아마 나중에 다시 켜면 될 거예요.
    // 네이티브 쿼리랑 차이가 있죠. 이 덕분에 디비 맘대로 바꿔도 영향 안 받아요.
    // 이 어노테이션 쓰면 원하는 조인이나 원하는 복잡한 쿼리도, 따로 마이바티스처럼 쓸 수 있어서
    // 내가 모르겠는 부분이 있다 -> 그냥 이런 어노테이션으로 급하게 처리해도 돼요. 아하
    // 근데 진짜 네이티브 쿼리랑 차이가 있어서 헷갈려요 저희는
    // select * from member where username = 1; 이런식으로 썼었는데 mem도 갑자기 튀어나오고..
    // 앨리어스 거의 안 써 봤죠. 네
    // 네이티브 쿼리 쓸 때도
    // select mem.* from member mem where mem.username = ... 이렇게 Alias 달아서 사용 가능해요.
    // 아 뭔가 기억이 날것같아요 이러면 컬럼명이 저렇게 바뀌어서 나왔던걳가틍ㄴ데..
    // 여기선 엔티티 클래스(자바) 기준으로 작성이 돼요.
    List<MemberEntity> 난가끔_내쪼대로_짓는다(String username);

    List<MemberEntity> findAll();

    int deleteByUsername(String username);
}

// 인터페이스끼리는 원래 extends 쓰니까 신경쓰지 말구요
// JpaRepository 인터페이스를 받아 놓으면
// 알아서 레포지터리 구현체가 빈으로 등록까지 다 돼요. 우린 아무것도 신경 안 써도 돼요. 기본 CRUD는 이제 쉽게 돼요.
// 방금 다 만든 거예요.

// 이제 기능 필요하면 여기 와서 함수 '이름'만 적으면 돼요.
// 와캬퍄.. 함수 이름은 제 마음대로 쓰는건가요? 아니면 양식이 있나요?
// 양식이 있지만 쉽긴 해요.

// 조회는
// find + (여기는 좀 자유롭게 작성) + By(= where절) + 컬럼이름('='로 비교) + ...

// 이제 그냥 뭐 작업할 때 필요한 거는 검색해서 나오는 걸로 치면 돼요.
// 잘 나와요. 원래 하던 설명 이어서 할게요.