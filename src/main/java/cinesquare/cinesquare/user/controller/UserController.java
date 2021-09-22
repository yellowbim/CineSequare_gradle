package cinesquare.cinesquare.user.controller;

import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.UserVO;
import cinesquare.cinesquare.user.service.MailService;
import cinesquare.cinesquare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
        resultMap.put("result", mailService.makeAuthNumMail(param));

        return resultMap;
    }

    // 회원정보
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST)
    public Map getUserInfo(@RequestBody UserVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.getUserInfo(param));

        return resultMap;
    }

    // 별점 주기(수정, 삭제)
    @RequestMapping(value = "/selectMovieGrade", method = RequestMethod.POST)
    public Map selectMovieGrade(@RequestBody GradeReviewVO param) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.selectMovieGrade(param));

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