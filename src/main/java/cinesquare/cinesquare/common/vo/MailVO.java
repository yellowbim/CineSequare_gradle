package cinesquare.cinesquare.common.vo;

import lombok.Data;

@Data
public class MailVO {

    private String recieverName; // 받는 사람 이름
    private String recieverMail; // 받는 사람 이메일 (회원가입 계정)
    private String title; // 제목
    private String content; // 내용
}
