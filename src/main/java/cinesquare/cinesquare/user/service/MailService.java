package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.vo.MailVO;
import cinesquare.cinesquare.common.vo.UserVO;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {

    // 메일 전송 모듈
    String setMailInfo(MailVO mail);

    // 인증번호 메일
    String makeAuthNumMail(UserVO param);

    // 이미지 삽입
//    public  void setInlineImage(String contentId, String pathToInline) throws MessagingException, IOException;
}
