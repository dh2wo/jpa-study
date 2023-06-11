CREATE TABLE IF NOT EXISTS MEMBER(
    id			    BIGINT			NOT NULL 	AUTO_INCREMENT PRIMARY KEY,
    username		VARCHAR(15)		NOT NULL 	COMMENT '아이디(계정)',
    password		VARCHAR(255)	NOT NULL,
    nickname	    VARCHAR(30)		NOT NULL,
    email		    VARCHAR(255)	NOT NULL,
    reg_date		DATETIME		NOT NULL	DEFAULT now(),
    role_name		VARCHAR(255) 	NOT NULL	DEFAULT 'ROLE_USER',

    CONSTRAINT uq_Member_username 	UNIQUE (username),
    CONSTRAINT uq_Member_nickname 	UNIQUE (nickname),
    CONSTRAINT uq_Member_email		UNIQUE (email)
);

-- NOTE 플라이웨이 1 파일 1 테이블(+ 인덱스 등은 같이 넣어도 됨.)
-- NOTE 이렇게 하시면 돼요. 파일 하나에 가급적 테이블 하나로 관리.

-- 아직 배포 같은 건 안 할 거니까, 플라이웨이 sql 파일들 바꾸고 나면, 테이블 다 날리고 애플리케이션 실행하면 돼요.
-- 일단 보여 줄게요.넵! 아니지 나중에 기훈 님한테 자세히 들어요 지금은 도커 컴포즈만 할게요