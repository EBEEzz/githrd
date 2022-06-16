CREATE TABLE surveyInfo(
    sino NUMBER(4)
        CONSTRAINT SI_NO_PK PRIMARY KEY,
    sititle VARCHAR2(50 CHAR)
        CONSTRAINT SI_TITLE_NN NOT NULL,
    sistart DATE,
    siend DATE
);

CREATE TABLE surveyQuest(
    sqno NUMBER(6)
        CONSTRAINT SQ_NO_PK PRIMARY KEY,
    sqbody VARCHAR2(50 CHAR)
        CONSTRAINT SQ_BODY_NN NOT NULL,
    squpno NUMBER(6),
    sq_sino NUMBER(4)
        CONSTRAINT SQ_SINO_FK REFERENCES surveyInfo(sino)
        CONSTRAINT SQ_SINO_NN NOT NULL
);

CREATE TABLE survey(
    svno NUMBER(6)
        CONSTRAINT SV_NO_PK PRIMARY KEY,
    smno NUMBER(4)
        CONSTRAINT SV_MNO_FK REFERENCES member(mno)
        CONSTRAINT SV_MNO_NN NOT NULL,
    sv_sqno NUMBER(6)
        CONSTRAINT SV_QNO_FK REFERENCES surveyQuest(sqno)
        CONSTRAINT SV_QNO_NN NOT NULL,
    svdate DATE DEFAULT sysdate
        CONSTRAINT SV_DATE_NN NOT NULL
);


INSERT INTO
    surveyInfo
VALUES(
    1001, '2022년 상반기 아이돌 선호도 조사', '2022/06/10', '2022/06/16'
);

-- 1번
INSERT INTO
    surveyQuest
VALUES(
    100001, '제일 좋아하는 여성 아이돌 그룹을 선택하세요.', null, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    100002, '블랙핑크', 100001, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    100003, '에스파', 100001, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    100004, '아이브', 100001, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    100005, '에이핑크', 100001, 1001
);

-- 2번
INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제일 좋아하는 남성 아이돌 그룹을 선택하세요.', null, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '샤이니', 100006, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '엑소', 100006, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), 'BTS', 100006, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '동방신기', 100006, 1001
);


-- 3번
INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제일 좋아하는 남자 아이돌 멤버를 선택하세요.', null, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), 'RM', 100011, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '유노윤호', 100011, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '차은우', 100011, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '런쥔', 100011, 1001
);


-- 4번
INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제일 좋아하는 여자 아이돌 멤버를 선택하세요.', null, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '태연', 100016, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제니', 100016, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '손나은', 100016, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '레이', 100016, 1001
);


-- 5번
INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제일 좋아하는 블랙핑크 멤버를 선택하세요.', null, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '제니', 100021, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '리사', 100021, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '로제', 100021, 1001
);

INSERT INTO
    surveyQuest
VALUES(
    (SELECT NVL(MAX(sqno) + 1, 100001) FROM surveyQuest), '지수', 100021, 1001
);

commit;

SELECT
    sqno qno, sqbody body, sq_sino
FROM
    surveyQuest
WHERE
    sq_sino IN (
        SELECT
            sino
        FROM
            surveyInfo
        WHERE
            sysdate BETWEEN sistart AND siend
    )
    AND squpno IS NULL
;

SELECT
    sqno qno, sqbody body
FROM
    surveyQuest
WHERE
    sq_sino IN (
        SELECT
            sino
        FROM
            surveyInfo
        WHERE
            sysdate BETWEEN sistart AND siend
    )
    AND squpno = 100001
;


-- 'jennie' 회원이 현재 진행중인 설문들 중 참여하지 않은 
-- 설문들의 갯수를 조회하는 질의명령을 작성하세요.
-- ==> 결과는 1이 조회되어야 한다.

INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1000, 100002
);

INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1000, 100009
);

INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1000, 100014
);

INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1000, 100018
);

INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1000, 100022
);


commit;

SELECT
    COUNT(*)
FROM
    surveyInfo
WHERE
    sysdate BETWEEN sistart AND siend
    AND sino NOT IN (
        SELECT
            DISTINCT sq_sino
        FROM
            survey, surveyquest, member
        WHERE
            sv_sqno = sqno
            AND smno = mno
            AND id = 'jennie'
    )
;


SELECT * FROM SURVEYQUEST;

SELECT
    sino, sititle, 
    (
        SELECT
            count(DISTINCT sq_sino)
        FROM
            survey, surveyQuest, member
        WHERE
            sv_sqno = sqno
            AND smno = mno
            AND id = 'euns'
        GROUP BY
            sq_sino
        HAVING
            sq_sino = sino
    ) cnt
FROM
    surveyinfo
WHERE
    sysdate BETWEEN sistart AND siend
;


-- 종료된 설문 리스트 가져오기 질의명령

SELECT
    sino, sititle title
FROM
    surveyinfo
WHERE
    siend < sysdate
;

-- 설문번호로 설문 주제 정보 조회 질의 명령
SELECT
    sino, sititle title
FROM
    surveyinfo
WHERE
    sino = 1001
;


-- 설문번호로 설문 문항 조회 질의명령
SELECT
    sqno qno, sqbody body, squpno upno
FROM
    surveyQuest
WHERE
    sq_sino = 1001
    AND squpno IS NULL
ORDER BY
    sqno
;

-- 문항번호로 보기 조회 질의명령
SELECT
    sqno qno, sqbody body 
FROM
    surveyQuest
WHERE
    squpno = 100001
ORDER BY
    sqno
;

-- 계층질의명령을 활용해서 문제와 보기 조회
SELECT
    sqno, sqbody body, NVL(squpno, sqno) upno
FROM
    surveyQuest
WHERE
    sq_sino = 1001
START WITH
    squpno IS NULL
CONNECT BY
    PRIOR sqno = squpno
ORDER SIBLINGS BY
    sqno
;

-----------------------------------------------
-- 응답 추가 질의명령
INSERT INTO
    survey(svno, smno, sv_sqno)
VALUES(
    (SELECT NVL(MAX(svno) + 1, 100001) FROM survey),
    1001, 100004
)
;

SELECT
    count(DISTINCT smno) total
FROM
    survey, surveyQuest
WHERE
    sv_sqno(+) = sqno
    AND sq_sino = 1001
;

-------------------------------------------------------
-- 문항 본문, 보기들, 보기들 득표율
-- 득표율 : 획득표 / 전체표

-- 각 문항별 득표 수
SELECT
    sqno, COUNT(sv_sqno) cnt
FROM
    surveyQuest, survey
WHERE
    sqno = sv_sqno(+)
    AND squpno IS NOT NULL
    AND sq_sino = 1001
GROUP BY
    sqno
;


-- 설문주제번호로 설문 결과 조회
SELECT
    sqno, sqbody body, NVL(squpno, sqno) upno, ROUND(cnt / total * 100, 2) per
FROM
    surveyQuest, 
    (
        SELECT
            sqno qno, COUNT(sv_sqno) cnt
        FROM
            surveyQuest, survey
        WHERE
            sqno = sv_sqno(+)
            AND squpno IS NOT NULL
            AND sq_sino = 1001
        GROUP BY
            sqno
        ORDER BY
            qno
    ),
    (
        SELECT
            count(DISTINCT smno) total
        FROM
            survey, surveyQuest
        WHERE
            sv_sqno(+) = sqno
            AND sq_sino = 1001
    )
WHERE
    sqno = qno(+)
ORDER BY
    upno, sqno
;

-- 설문 문항번호로 보기 정보를 조회하는 질의명령
SELECT
    sqno, sqbody body, NVL(squpno, sqno) upno, ROUND(cnt / total * 100, 2) per
FROM
    surveyQuest, 
    (
        SELECT
            sqno qno, COUNT(sv_sqno) cnt
        FROM
            surveyQuest, survey
        WHERE
            sqno = sv_sqno(+)
            AND squpno = 100001
        GROUP BY
            sqno
        ORDER BY
            qno
    ),
    (
        SELECT
            count(DISTINCT smno) total
        FROM
            survey, surveyQuest
        WHERE
            sv_sqno(+) = sqno
            AND squpno = 100001
    )
WHERE
    sqno = qno
ORDER BY
    upno, sqno
;










