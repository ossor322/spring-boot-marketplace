-- market_member 테이블에 대한 테스트 데이터
INSERT INTO market_member (member_id, member_pw, member_name, phone)
VALUES ('user01', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Alice', '010-1234-5678'),
       ('user02', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Bob', '010-2345-6789'),
       ('user03', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Charlie', '010-3456-7890'),
       ('user04', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'David', '010-4567-8901'),
       ('user05', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Eve', '010-5678-9012'),
       ('user06', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Frank', '010-6789-0123'),
       ('user07', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Grace', '010-7890-1234'),
       ('user08', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Hank', '010-8901-2345'),
       ('user09', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Ivy', '010-9012-3456'),
       ('user10', '$2a$10$A6PAbXZ97A8cUrbOJZa1tuXZHZlRQL4IXyp6fJdcKF4RjBDza9HdK', 'Jack', '010-0123-4567');

-- market_board 테이블에 대한 테스트 데이터
INSERT INTO market_board (member_id, category, title, contents, price, soldout, buyer_id)
VALUES ('user01', '컴퓨터', '게이밍 PC 팝니다', '고사양 게이밍 PC, SSD 512GB 포함', 1200000, 0, NULL),
       ('user02', '카메라', '카메라 렌즈 팝니다', '캐논 렌즈 50mm f1.8', 150000, 0, NULL),
       ('user03', '자동차', '중고차 팝니다', '연비 좋은 중고차, 흰색', 5000000, 1, 'user05'),
       ('user04', '컴퓨터', '모니터 팝니다', '24인치 모니터, 상태 좋음', 80000, 0, NULL),
       ('user05', '카메라', '중고 DSLR 팝니다', '니콘 D750, 사용감 있음', 600000, 1, 'user03'),
       ('user06', '자동차', '오래된 SUV 팝니다', 'SUV, 주행거리 15만km', 3000000, 0, NULL),
       ('user07', '컴퓨터', '노트북 팝니다', 'i7 프로세서, 램 16GB', 1000000, 1, 'user02'),
       ('user08', '카메라', '삼각대 팝니다', '카메라용 삼각대, 거의 새것', 50000, 0, NULL),
       ('user09', '자동차', '스쿠터 팝니다', '50cc 스쿠터, 연비 좋음', 600000, 0, NULL),
       ('user10', '컴퓨터', '그래픽 카드 팝니다', 'GTX 1660, 사용 6개월', 250000, 0, NULL);

-- market_reply 테이블에 대한 테스트 데이터
INSERT INTO market_reply (board_num, member_id, reply_text)
VALUES (1, 'user02', '가격 좀 더 낮춰주실 수 있나요?'),
       (1, 'user01', '죄송하지만 이 가격이 최선입니다.'),
       (2, 'user03', '렌즈 상태 어떤가요?'),
       (2, 'user02', '사용감은 있지만 기능 문제 없습니다.'),
       (3, 'user04', '이 차 사고 이력 있나요?'),
       (4, 'user05', '모니터 아직 팔렸나요?'),
       (5, 'user06', 'DSLR 카메라 가격 좀 더 깎아주세요.'),
       (6, 'user07', 'SUV 차량 관심 있습니다.'),
       (7, 'user08', '노트북 배터리 상태 어떤가요?'),
       (8, 'user09', '삼각대 높이는 어느 정도 되나요?');

commit;

select * from market_member;
select * from market_board;
select * from market_reply;