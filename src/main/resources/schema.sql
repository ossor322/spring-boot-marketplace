use mydb;

drop table if exists market_reply;
drop table if exists market_board;
drop table if exists market_member;

-- 테이블 1: market_member
CREATE TABLE market_member
(
    member_id   VARCHAR(20) PRIMARY KEY, -- 사용자 아이디
    member_pw   VARCHAR(100) NOT NULL,   -- 암호화된 로그인 비밀번호
    member_name VARCHAR(20)  NOT NULL,   -- 사용자 이름
    phone       VARCHAR(20)  NOT NULL    -- 전화번호
);

-- 테이블 2: market_board
CREATE TABLE market_board
(
    board_num  INT PRIMARY KEY AUTO_INCREMENT,      -- 글 번호, 자동증가
    member_id  VARCHAR(20),                         -- 작성자 아이디
    category   VARCHAR(50)   NOT NULL
        CHECK (category IN ('컴퓨터', '카메라', '자동차')),  -- 상품분류 (컴퓨터/카메라/자동차)
    title      VARCHAR(200)  NOT NULL,              -- 제목
    contents   VARCHAR(2000) NOT NULL,              -- 상품소개글 내용
    price      INT       DEFAULT 0,                 -- 판매 가격
    soldout    TINYINT(1)   DEFAULT 0,              -- 판매완료여부 (판매중:0, 판매완료:1)
    buyer_id   VARCHAR(20),                         -- 구매자 아이디
    input_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 작성일

    -- Foreign Key 제약조건
    FOREIGN KEY (member_id) REFERENCES market_member (member_id),
    FOREIGN KEY (buyer_id) REFERENCES market_member (member_id)
);

-- 테이블 3: market_reply
CREATE TABLE market_reply
(
    reply_num  INT PRIMARY KEY AUTO_INCREMENT, -- 리플 일련번호
    board_num  INT,                            -- 판매글 본문 번호
    member_id  VARCHAR(20),                    -- 작성자 아이디
    reply_text VARCHAR(500) NOT NULL,          -- 리플 내용

    -- Foreign Key 제약조건
    FOREIGN KEY (board_num) REFERENCES market_board (board_num),
    FOREIGN KEY (member_id) REFERENCES market_member (member_id)
);

use mydb;
select * from market_member;
