SELECT * FROM "MEMBER";
--1번부터 5번까지 데이터 가져오기
--rownum : 오라클리 제공하는 가상컬럼
SELECT *
FROM (SELECT rownum AS rnum, m.* 
	FROM (SELECT * FROM MEMBER M ORDER BY enrolldate DESC) m)
WHERE rnum BETWEEN 6 AND 10; 
--BETWEEN AND 사이의 값이 시작, 끝 rownum값이 들어가야한다

