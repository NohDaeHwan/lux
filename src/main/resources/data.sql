INSERT INTO user_grade(id, discount, grade_step, grade_name, rank_up) VALUES (1,0,1,'BRONZE',0),(2,5,2,'SLIVER',100000),
                              (3,10,3,'GOLD',500000),(4,15,4,'VIP',1000000),(6,20,5,'SVIP',3000000);


INSERT INTO category_b(id, category_b_name) VALUES (1,'악세서리'),(2,'가방'),(3,'옷'),(4,'안경'),(5,'쥬얼리'),(6,'신발'),(7,'시계'),(8,'지갑');

INSERT INTO category_m(id, category_b_id, category_m_name)VALUES (1,1,'귀걸이'),(2,1,'넥타이'),(3,1,'라이터'),(4,1,'모자'),(5,1,'머플러'),(6,1,'목걸이'),
                              (7,1,'반지'),(8,1,'벨트'),(9,1,'볼펜'),(10,1,'브로치'),(11,1,'스카프'),(12,1,'스카프링'),
                              (13,1,'장갑'),(14,1,'장갑'),(15,1,'키링'),(16,1,'팔찌'),(17,1,'헤어밴드'),(18,1,'기타'),
                              (19,2,'백팩'),(20,2,'서류가방'),(21,2,'세컨백'),(22,2,'숄더백'),(23,2,'여행가방'),
                              (24,2,'크로스백'),(25,2,'토트백'),(26,2,'파우치'),(27,2,'힙색'),(28,2,'클러치'),(29,2,'기타'),
                              (30,3,'긴팔'),(31,3,'반팔'),(32,3,'민소매'),(33,3,'베스트'),(34,3,'바지'),(35,3,'데님바지'),
                              (36,3,'반바지'),(37,3,'스커트'),(38,3,'가디건'),(39,3,'자켓'),(40,3,'점퍼'),(41,3,'코트'),
                              (42,3,'트렌치코트'),(43,3,'원피스'),(44,3,'투피스'),(45,3,'정장'),(46,3,'트윈세트'),
                              (47,3,'수영복'),(48,3,'기타'),(49,4,'선글라스'),(50,4,'안경'),(51,5,'귀걸이'),(52,5,'목걸이'),
                              (53,5,'반지'),(54,5,'팔찌'),(55,5,'브로치'),(56,5,'펜던트'),(57,5,'세트상품'),(58,5,'기타'),
                              (59,6,'구두'),(60,6,'로퍼'),(61,6,'뮬'),(62,6,'부츠'),(63,6,'샌들'),(64,6,'슈즈'),
                              (65,6,'스니커즈'),(66,6,'슬리퍼'),(67,6,'슬링백'),(68,6,'플랫슈즈'),(69,6,'기타'),
                              (70,7,'시계'),(71,7,'시계밴드'),(72,8,'다이어리'),(73,8,'동전지갑'),(74,8,'카드/명함지갑'),
                              (75,8,'반지갑'),(76,8,'장지갑'),(77,8,'중지갑'),(78,8,'키지갑'),(79,8,'기타');

INSERT INTO brand(id, brand_name) VALUES (1,'가니'),(2,'가브리엘 허스트'),(3,'겐조'),(4,'고야드'),(5,'골든구스'),(6,'구찌'),
                         (7,'그라함'),(8,'그렌슨'),(9,'글라슈테 오리지널'),(10,'기타'),(11,'까르띠에'),(12,'까르벵'),
                         (13,'까사렐'),(14,'까스텔바작'),(15,'꼼데가르송'),(16,'끌로엦'),(17,'넘버21'),(18,'노비스'),
                         (19,'닐바렛'),(20,'다미아니'),(21,'다사끼'),(22,'더 로우'),(23,'델보'),(24,'돌체앤가바나'),
                         (25,'드리스반노튼'),(26,'디스퀘어드2'),(27,'디올옴므'),(28,'디케이엔와이'),(29,'띠어리'),
                         (30,'라프 시몬스'),(31,'랄프로렌'),(32,'랑방'),(33,'랑에 운트 죄네'),(34,'랙앤본'),(35,'레베카 테일러'),
                         (36,'로렉스'),(37,'로로피아나'),(38,'로베르토카발리'),(39,'로샤스'),(40,'로에베');

INSERT INTO state(id, state_name, state_step) VALUES (1,'검수','검수전'),(2,'검수','검수중'),(3,'검수','검수완료'),(4,'상품','신규'),
                         (5,'상품','판매대기'),(6,'상품','판매중'),(7,'상품','판매완료'),(8,'상품','판매취소'),
                         (9,'경매','경매전'),(10,'경매','경매중'),(11,'경매','경매완료'),(12,'주문','주문완료'),
                         (13,'주문','주문취소'),(14,'주문','취소요청');

INSERT INTO user_account(id, user_email, user_password, user_name, phone_number, age, gender, user_grade, point, role, memo, created_at, created_by, modified_at, modified_by) VALUES
(1,'qwer1234@naver.com','$2a$10$Cd4xXlpbYzq6zVvxBuFJZ.QNxADDn4u7/Q4.sICp39h42.nj/YUGi','다람쥐','010-1234-5678',25,'Male',4,1099999,'USER','TEST USER','2022-11-18 12:49:47.949772','SYSTEM','2022-11-18 16:43:53.082205','SYSTEM'),
(2,'asdf1234@naver.com','$2a$10$y28559SHwV0M.zpp9bEWbu.mygAsnr.DVbNPGxeXif//osUNlA5U2','Admin다람쥐쥐','010-2345-6789',26,'남자',1,50000,'ADMIN','TEST USER','2022-11-18 14:14:01.213608','SYSTEM','2022-11-21 17:55:58.143390','SYSTEM');

INSERT INTO brand(id, brand_name) VALUES (1,'가니'),(2,'가브리엘 허스트'),(3,'겐조'),(4,'고야드'),(5,'골든구스'),(6,'구찌'),
                                         (7,'그라함'),(8,'그렌슨'),(9,'글라슈테 오리지널'),(10,'기타'),(11,'까르띠에'),(12,'까르벵'),
                                         (13,'까사렐'),(14,'까스텔바작'),(15,'꼼데가르송'),(16,'끌로엦'),(17,'넘버21'),(18,'노비스'),
                                         (19,'닐바렛'),(20,'다미아니'),(21,'다사끼'),(22,'더 로우'),(23,'델보'),(24,'돌체앤가바나'),
                                         (25,'드리스반노튼'),(26,'디스퀘어드2'),(27,'디올옴므'),(28,'디케이엔와이'),(29,'띠어리'),
                                         (30,'라프 시몬스'),(31,'랄프로렌'),(32,'랑방'),(33,'랑에 운트 죄네'),(34,'랙앤본'),(35,'레베카 테일러'),
                                         (36,'로렉스'),(37,'로로피아나'),(38,'로베르토카발리'),(39,'로샤스'),(40,'로에베');

INSERT INTO state(id, state_name, state_step) VALUES (1,'검수','검수전'),(2,'검수','검수중'),(3,'검수','검수완료'),(4,'상품','신규'),
                                                     (5,'상품','판매대기'),(6,'상품','판매중'),(7,'상품','판매완료'),(8,'상품','판매취소'),
                                                     (9,'경매','경매전'),(10,'경매','경매중'),(11,'경매','경매완료'),(12,'주문','주문완료'),
                                                     (13,'주문','주문취소'),(14,'주문','취소요청');
