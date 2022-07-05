CREATE TABLE singerType(
    no NUMBER(2)
        CONSTRAINT SINGERTYPE_NO_PK PRIMARY KEY,
    a_time VARCHAR2(2 CHAR)
        CONSTRAINT SINGERTYPE_TIME_CK CHECK (a_time IN ('현재', '과거'))
        CONSTRAINT SINGERTYPE_TIME_NN NOT NULL,
    s_gen VARCHAR2(2 CHAR)
        CONSTRAINT SINGERTYPE_GEN_CK CHECK (s_gen IN('남자', '여자'))
        CONSTRAINT SINGERTYPE_GEN_NN NOT NULL
)
;

INSERT INTO
    singerType
VALUES(
    (SELECT NVL(MAX(no) + 1, 11) FROM singerType),
    '현재', '남자'
);

INSERT INTO
    singerType
VALUES(
    (SELECT NVL(MAX(no) + 1, 11) FROM singerType),
    '현재', '여자'
);

INSERT INTO
    singerType
VALUES(
    (SELECT NVL(MAX(no) + 1, 11) FROM singerType),
    '과거', '남자'
);

INSERT INTO
    singerType
VALUES(
    (SELECT NVL(MAX(no) + 1, 11) FROM singerType),
    '과거', '여자'
);

CREATE TABLE singer(
    num NUMBER(4)
        CONSTRAINT SINGER_NUM_PK PRIMARY KEY,
    s_type NUMBER(2)
        CONSTRAINT SINGER_TYPE_FK REFERENCES singerType(no)
        CONSTRAINT SINGER_TYPE_NN NOT NULL,
    name VARCHAR2(50 CHAR)
        CONSTRAINT SINGER_NAME_NN NOT NULL,
    scode CHAR(1)
        CONSTRAINT SINGER_CODE_CK CHECK (scode IN('G', 'M', 'S'))
        CONSTRAINT SINGER_CODE_NN NOT NULL,
    gno NUMBER(4)
)
;


INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '블랙핑크', 'G', null
);


INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '제니', 'M', 1001
);

INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '리사', 'M', 1001
);

INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '로제', 'M', 1001
);

INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '지수', 'M', 1001
);

INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    13, '소방차', 'G', null
);
INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    13, '정원관', 'M', 1006
);
INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    13, '도건우', 'M', 1006
);
INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    13, '김태형', 'M', 1006
);

INSERT INTO
    singer
VALUES(
    (SELECT NVL(MAX(num) + 1, 1001) FROM singer),
    12, '아이유', 'S', null
);

CREATE TABLE photo(
    pno NUMBER(6)
        CONSTRAINT PHOTO_NO_PK PRIMARY KEY,
    s_num NUMBER(4)
        CONSTRAINT PHOTO_SNUM_FK REFERENCES singer(num)
        CONSTRAINT PHOTO_SNUM_NN NOT NULL,
    oname VARCHAR2(200 CHAR)
        CONSTRAINT PHOTO_ONAME_NN NOT NULL,
    sname VARCHAR2(200 CHAR)
        CONSTRAINT PHOTO_SNAME_NN NOT NULL
        CONSTRAINT PHOTO_SNAME_UK UNIQUE,
    dir VARCHAR2(200 CHAR)
        CONSTRAINT PHOTO_DIR_NN NOT NULL,
    len NUMBER
        CONSTRAINT PHOTO_LEN_NN NOT NULL,
    show CHAR(1) DEFAULT 'Y'
        CONSTRAINT PHOTO_SHOW_CK CHECK (show IN('C', 'Y', 'N'))
        CONSTRAINT PHOTO_SHOW_NN NOT NULL
)
;

TRUNCATE TABLE photo;

INSERT INTO
    photo
VALUES(
    (SELECT NVL(MAX(pno) + 1, 100001) FROM photo),
    1002, 'jennie.jpg', 'jennie.jpg', '/img/photo/', 52749, 'C'
)
;


INSERT INTO
    photo
VALUES(
    (SELECT NVL(MAX(pno) + 1, 100001) FROM photo),
    1003, 'lisa.jpg', 'lisa.jpg', '/img/photo/', 1215856, 'C'
)
;

INSERT INTO
    photo
VALUES(
    (SELECT NVL(MAX(pno) + 1, 100001) FROM photo),
    1003, 'rose.jpg', 'rose.jpg', '/img/photo/', 76239, 'C'
)
;

INSERT INTO
    photo
VALUES(
    (SELECT NVL(MAX(pno) + 1, 100001) FROM photo),
    1004, 'jisoo.jpeg', 'jisoo.jpeg', '/img/photo/', 79998, 'C'
)
;

INSERT INTO
    photo
VALUES(
    (SELECT NVL(MAX(pno) + 1, 100001) FROM photo),
    1010, 'iu.jpeg', 'iu.jpeg', '/img/photo/', 266934, 'C'
)
;

commit;
