package cinesquare.cinesquare.user.controller;

import cinesquare.cinesquare.common.vo.MailVO;
import cinesquare.cinesquare.common.vo.UserVO;
import cinesquare.cinesquare.user.service.MailService;
import cinesquare.cinesquare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    // CINE 회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Map signup(@RequestBody UserVO param) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", userService.signup(param));

        return resultMap;
    }

    // CINE 로그인
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Map signin(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.getUser(param));

        return resultMap;
    }

    // CINE 계정 중복확인
    @RequestMapping(value = "/signup/valid", method = RequestMethod.POST)
    public Map signupValid(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.validAccount(param.getAccount()));

        return resultMap;
    }

    // 회원가입 메일 전송
    @RequestMapping(value = "/signup/sendmail", method = RequestMethod.POST)
    public Map sendMailTest(@RequestBody UserVO param) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        MailVO authNumMail = mailService.makeAuthNumMail(param);
        resultMap.put("result", mailService.sendMail(authNumMail));

        return resultMap;
    }
}