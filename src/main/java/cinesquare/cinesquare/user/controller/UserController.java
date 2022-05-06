package cinesquare.cinesquare.user.controller;

import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.UserVO;
import cinesquare.cinesquare.user.service.MailService;
import cinesquare.cinesquare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://cinesquare.yahmedora.com:8088", "http://54.180.29.206:8088", "https://graceful-starburst-2727fb.netlify.app", "https://cinesquare.netlify.app", "https://cinesquare.slowtuttle.co.kr:8088", "http://cinesquare.slowtuttle.co.kr:8088"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    // health check
    @RequestMapping(value = "/health", method = RequestMethod.POST)
    public Map health() throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "I'm alive");

        return resultMap;
    }

    // CINE 회원가입
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Map signup(@RequestBody UserVO param) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", userService.signup(param));

        return resultMap;
    }

    // 소셜 회원가입
    @RequestMapping(value = "/apiSignup", method = RequestMethod.POST)
    public Map apiSignup(@RequestBody UserVO param) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", userService.apiSignup(param));

        return resultMap;
    }

    // CINE 로그인
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Map signin(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.getUserInfo(param));

        return resultMap;
    }

    // CINE 계정 중복확인
    @RequestMapping(value = "/signup/valid", method = RequestMethod.POST)
    public Map signupValid(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.signupValid(param.getAccount()));

        return resultMap;
    }

    // 회원가입 인증 메일 전송
    @RequestMapping(value = "/signup/sendAuthMail", method = RequestMethod.POST)
    public Map sendMailTest(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        String authNum = mailService.makeRandomNum();
        String result = mailService.makeAuthNumMail(param, authNum);
        String code = result.equals("success") ? authNum : "";

        resultMap.put("result", result);
        resultMap.put("code", code);

        return resultMap;
    }

    // 회원정보
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public Map getUserInfo(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.getUserInfo(param));

        return resultMap;
    }

    // 회원정보 수정
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public Map updateUserInfo(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.updateUserInfo(param));

        return resultMap;
    }

    // 별점 주기(수정, 삭제)
    @RequestMapping(value = "/selectMovieGrade", method = RequestMethod.POST)
    public Map selectMovieGrade(@RequestBody GradeReviewVO param) throws Exception {
        Boolean processResult = false;
        
        // 해당 유저가 존재한다는 전제 하에 프로세스 진행
        if (!isEmpty(param.getGrade()) && !isEmpty(param.getMovieCd())) {
            if (!isEmpty(param.getCineToken())
                    || (!isEmpty(param.getAccount()) && !isEmpty(param.getPassword()))) {
                processResult = userService.selectMovieGrade(param);
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", processResult);

        return resultMap;
    }

    // 별점 리스트
    @RequestMapping(value = "/gradeList", method = RequestMethod.POST)
    public Map getGradeList(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.listGradeList(param));

        return resultMap;
    }

    // 영화별 별점/리뷰
    @RequestMapping(value = "/gradeAndReview", method = RequestMethod.POST)
    public Map getGradeAndReview(@RequestBody GradeReviewVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.getGradeAndReview(param));

        return resultMap;
    }

    // 평가한 영화/평점 리스트
    @RequestMapping(value = "/userMovieGrade", method = RequestMethod.POST)
    public Map listUserMovieGrade(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.listUserMovieGrade(param));

        return resultMap;
    }

    // 평가한 리뷰 리스트
    @RequestMapping(value = "/userMovieReview", method = RequestMethod.POST)
    public Map listUserMovieReview(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.listUserMovieReview(param));

        return resultMap;
    }
}