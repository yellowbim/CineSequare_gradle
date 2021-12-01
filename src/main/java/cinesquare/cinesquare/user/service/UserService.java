package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    String signup(UserVO param) throws Exception;

    String apiSignup(UserVO param) throws Exception;

    boolean signupValid(String param) throws Exception;

    UserVO getUserInfo(UserVO param) throws Exception;

    UserVO updateUserInfo(UserVO param) throws Exception;

    Boolean selectMovieGrade(GradeReviewVO param) throws Exception;

    List<Map<String, String>> listGradeList(UserVO param) throws Exception;

    GradeReviewVO getGradeAndReview(GradeReviewVO param) throws Exception;

    List<GradeReviewVO> listUserMovieGrade(UserVO param) throws Exception;

    List<GradeReviewVO> listUserMovieReview(UserVO param) throws Exception;

}
