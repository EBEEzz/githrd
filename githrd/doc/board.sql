-- 게시글 리스트 조회 질의 명령


SELECT
    bno, id, title, wdate, click, cnt
FROM
    (
        SELECT
            ROWNUM rno, bno, id, title, wdate, click, cnt
        FROM
            (
                SELECT
                    bno, id, title, wdate, click, cnt
                FROM
                    board, member,
                    (
                        SELECT
                            bno fbno, NVL(COUNT(fno), 0) cnt
                        FROM
                            board, fileInfo
                        WHERE
                            bno = fbno(+)
                        GROUP BY
                            bno
                    )
                WHERE
                    bno = fbno
                    AND bmno = mno
                    AND board.isshow = 'Y'
                ORDER BY
                    bno DESC
            )
    )
WHERE
    rno BETWEEN 1 AND 3
;
-- 게시글  첨부파일 갯수 조회 질의 명령(글번호, 파일갯수)
-- ==> 첨부 파일이 없는 경우는 0개로 조회되게 하세요.
SELECT
    bno, nvl(count(*),0) fakeCount, NVL(COUNT(fno), 0) cnt
FROM
    board, fileInfo
WHERE
    bno = fbno(+)
GROUP BY
    bno
;

SELECT
    bno, fno
FROM
    board, fileinfo
WHERE
    bno = fbno(+)
;


-- 게시글 내용 조회 질의명령
-- 글번호, 아이디, 타이틀, 본문, 작성일

SELECT
    bno, id, title, body, wdate
FROM
    board b, member m
WHERE
    bmno = mno
    AND b.isshow = 'Y'
    AND bno = #{bno} 
;


-- 게시글의 첨부파일 조회 질의명령
SELECT
    savename, dir
FROM
    fileinfo
WHERE
    isshow = 'Y'
    AND fbno = 100002
;