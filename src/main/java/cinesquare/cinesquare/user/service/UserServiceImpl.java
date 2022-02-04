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
        return userMapper.validSignup(param) == 0 ? true : false;
    }

    // 회원정보
    @Override
    public UserVO getUserInfo(UserVO param) throws Exception {
        return userMapper.getUserInfo(param);
    }

    // 회원정보 수정
    @Override
    public UserVO updateUserInfo(UserVO param) throws Exception {
        UserVO userInfo = new UserVO();
        int result = userMapper.updateUserInfo(param);
        if (result > 0) {
            userInfo = userMapper.getUserInfo(param);
        }

        return userInfo;
    }

    // 별점 주기
    @Override
    public Boolean selectMovieGrade(GradeReviewVO param) throws Exception {
        Boolean allProcessResult = false;
        // 기존에 몇점을 주었는가, 준 기록이 없다면 null
        String oldGrade = userMapper.checkMovieGrade(param);
        // 기존 시청시간
        param.setTotalWatchTime(userMapper.checkTotalWatchTime(param));

        // 사용자별 영화 점수 부여, 변경, 삭제
        allProcessResult = userJudgeMovieGrade(param, oldGrade) ? true : false;

        if (allProcessResult) {
            // 영화별 전체 사용자의 별점 기록
            allProcessResult = movieService.updateMovieGradeReport(param, oldGrade) ? true : false;

            if (allProcessResult) {
                // 사용자별 총 시청 시간
                allProcessResult = changeUserWatchTime(param, oldGrade) ? true : false;
                if (!allProcessResult) {
                    System.out.println("별점 오류3: changeUserWatchTime");
                }
            } else {
                System.out.println("별점 오류2: updateMovieGradeReport");
            }
        } else {
            System.out.println("별점 오류1: userJudgeMovieGrade");
        }


        return allProcessResult;
    }

    // 사용자별 영화 별점 부여, 삭제, 변경
    @Override
    public Boolean userJudgeMovieGrade(GradeReviewVO param, String oldGrade) throws Exception {
        int processResult = -1;

        // 별점 부여 기록 없음 -> 새로 별점 부여
        if (isEmpty(oldGrade)) {
            processResult = userMapper.insertUserMovieGrade(param);
        }
        // 새로 부여한 별점이 0점 -> 기존 별점 부여 기록 delete
        else if("0".equals(param.getGrade())) {
            processResult = userMapper.deleteUserMovieGrade(param);
        }
        // 새로 부여한 별점이 0점 X -> 새로 부여한 별점으로 update
        else {
            processResult = userMapper.updateUserMovieGrade(param);
        }

        return processResult > 0 ? true : false;
    }

    // 시청시간 업데이트
    @Override
    public Boolean changeUserWatchTime(GradeReviewVO param, String oldGrade) throws Exception {
        int processResult = -1;

        // 별점 부여 기록 없음 -> 시청시간 올리기
        if (isEmpty(oldGrade)){
            processResult = userMapper.countUpWatchTime(param);
        }
        // 새로 부여한 별점이 0점 -> 시청시간 내리기
        else if("0".equals(param.getGrade())) {
            processResult = userMapper.countDownWatchTime(param);
        }
        // 새로 부여한 별점이 0점 X -> 시청시간 변경 없음
        else {
            processResult = 1;
        }

        return processResult > 0 ? true : false;
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
//        String movieNm = movieMapper.getSimpleMovieInfo(param.getMovieCd()).getMovieNm();
//        movieGradeAndReview.setMovieNm(movieNm);
        
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