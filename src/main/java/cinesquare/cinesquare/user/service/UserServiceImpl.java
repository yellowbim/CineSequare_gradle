package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.mapper.MovieMapper;
import cinesquare.cinesquare.common.mapper.UserMapper;
import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.UserVO;
import cinesquare.cinesquare.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieService movieService;

    // CINE 회원가입
    @Override
    public String signup(UserVO user) throws Exception {
        String rtnText = "success";
        try {
            userMapper.signup(user);
        } catch (Exception e){
            rtnText = "fail";
        }
        return rtnText;
    }

    // api 회원가입 test
    @Override
    public String apiSignup(UserVO user) throws Exception {
        String rtnText = "";
        try {
            userMapper.apiSignup(user);
            rtnText = userMapper.last();
        } catch (Exception e){
            rtnText = "fail";
        }
        return rtnText;
    }

    // 계정 중복확인
    @Override
    public boolean signupValid(String param) throws Exception {
        boolean rtnText = false;
        if (userMapper.validSignup(param) == 0) rtnText = true;

        return rtnText;
    }

    // 회원정보
    @Override
    public UserVO getUserInfo(UserVO param) throws Exception {
        return userMapper.getUserInfo(param);
    }

    // 별점 주기
    @Override
    public String selectMovieGrade(GradeReviewVO param) throws Exception {
        GradeReviewVO tmpParam = new GradeReviewVO();
        tmpParam = param;
        boolean movieProcessResult = movieService.updateMovieGrade(tmpParam);

        int userProcessResult;
        if ("0".equals(param.getGrade()) || isEmpty(param.getGrade())) {
            userProcessResult = userMapper.deleteMovieGrade(param);
        } else {
            int check = userMapper.checkMovieGrade(param);
            if (check > 0) {
                userProcessResult = userMapper.updateMovieGrade(param);
            } else {
                userProcessResult = userMapper.insertMovieGrade(param);
            }
        }

        String result = userProcessResult > 0 && movieProcessResult == true ? "success" : "fail";

        return result;
    }

    // 별점 리스트
    @Override
    public List<Map<String, String>> listGradeList(UserVO param) throws Exception {
        return userMapper.listGradeList(param);
    }

    // 영화별 별점/리뷰
    @Override
    public GradeReviewVO getGradeAndReview(GradeReviewVO param) throws Exception {
        GradeReviewVO movieGradeAndReview = userMapper.getGradeAndReview(param);
        String movieNm = movieMapper.getSimpleMovieInfo(param.getMovieCd()).getMovieNm();
        movieGradeAndReview.setMovieNm(movieNm);
        
        return movieGradeAndReview;
    }

    // 평가한 영화/평점 리스트
    @Override
    public List<GradeReviewVO> listUserMovieGrade(UserVO param) throws Exception {
        List<GradeReviewVO> movieGradeAndReviewList = userMapper.listUserMovieGrade(param);

        return movieGradeAndReviewList;
    }

    // 평가한 리뷰 리스트
    @Override
    public List<GradeReviewVO> listUserMovieReview(UserVO param) throws Exception {
        List<GradeReviewVO> movieGradeAndReviewList = userMapper.listUserMovieReview(param);

        return movieGradeAndReviewList;
    }
}