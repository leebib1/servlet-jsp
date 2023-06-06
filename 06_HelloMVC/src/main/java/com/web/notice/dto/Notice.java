package com.web.notice.dto;

import java.sql.Date;

import com.web.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
//	NOTICE_NO NUMBER PRIMARY KEY,
//    NOTICE_TITLE VARCHAR2(100) NOT NULL,
//    NOTICE_WRITER VARCHAR2(15) NOT NULL,
//    NOTICE_CONTENT VARCHAR2(4000) NOT NULL,
//    NOTICE_DATE DATE DEFAULT SYSDATE,
//    FILEPATH VARCHAR2(300),
//    STATUS VARCHAR2(1) DEFAULT 'Y'
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private Date noticeDate;
	private String filepath;
	private String status;
	
}
