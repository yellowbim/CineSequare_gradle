package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.UserContentsVO;
import cinesquare.cinesquare.common.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    int signup(UserVO param) throws IllegalAccessException;

    int apiSignup(UserVO param) throws IllegalAccessException;

    String last() throws IllegalAccessException;

    int validSignup(String param) throws IllegalAccessException;

    UserVO getUserInfo(UserVO param) throws IllegalAccessException;

    int updateUserInfo(UserVO param) throws IllegalAccessException;

    String checkMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int insertMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int updateMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int deleteMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int countUpWatchTime(GradeReviewVO param) throws IllegalAccessException;

    int countDownWatchTime(GradeReviewVO param) throws IllegalAccessException;

    List<Map<String, String>> listGradeList(UserVO param) throws IllegalAccessException;

    GradeReviewVO getGradeAndReview(GradeReviewVO param) throws IllegalAccessException;

    List<GradeReviewVO> listUserMovieGrade(UserVO param) throws IllegalAccessException;

    List<GradeReviewVO> listUserMovieReview(UserVO param) throws IllegalAccessException;
}
