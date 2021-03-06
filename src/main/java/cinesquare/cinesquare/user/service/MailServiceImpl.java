package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.vo.MailVO;
import cinesquare.cinesquare.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String setMailInfo(MailVO mail) {
        Map<String, String> rtnMap = new HashMap<>();
        String result = "";

        try {
            // 이메일 객체, createMimeMessage() : MimeMessage객체를 생성시킴 (이거로 메시지를 구성한 뒤 메일 발송)
            MimeMessage msg = javaMailSender.createMimeMessage();

            // 수신자 설정 (수신자의 이메일 주소 객체를 생성해서 이메일 주소를 담음)
            // addRecipient() : 메시지의 발신자를 설정, MimeMessage.RecipientType.TO : 타입, InternetAddress() : 이메일 주소
            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail.getRecieverMail()));

            // 이메일 제목, 내용
            msg.setSubject(mail.getTitle());
            msg.setText(mail.getContent(), "utf-8");

            // 메일 보내기
            javaMailSender.send(msg);

            result = "success";
        } catch (Exception e) {
            result = "fail";
            System.out.println("ERROR : " + e.getMessage());
        }

        return result;
    }

    @Override
    public String makeAuthNumMail(UserVO param, String authNum) {
        MailVO mail = new MailVO();

        String content = "[CINESQUARE]\n"
                + "\n반갑습니다."
                + "\nCINESQUARE 계정 생성을 위한 인증번호는 " + authNum + " 입니다.";

        mail.setTitle("[CINESQUARE] 계정생성 인증번호");
        mail.setContent(content);
        mail.setRecieverMail(param.getAccount());

        String rtnMsg = setMailInfo(mail);

        return rtnMsg;
    }

    @Override
    public String makeRandomNum() {
        String randomNum = String.valueOf((int) (Math.random() * (999999 - 100000 + 1)) + 100000);

        return randomNum;
    }

    // 이미지 삽입
//    @Override
//    public void setInlineImage(String contentId, String pathToInline) throws MessagingException, IOException {
//    }

}
