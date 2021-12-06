package cinesquare.cinesquare.movie.service;

import cinesquare.cinesquare.common.mapper.MovieMapper;
import cinesquare.cinesquare.common.vo.CharacterVO;
import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    // 영화 목록
    @Override
    public List<MovieVO> getMovieList(String param) throws Exception {
        List<MovieVO> movieList = movieMapper.searchMovie(param);

        return movieList;
    }

    // 박스오피스 목록
    @Override
    public List<Map> getBoxoffice() throws Exception {
        List<Map> movieList = movieMapper.getBoxoffice();

        return movieList;
    }

    // 영화 상세 정보
    @Override
    public MovieVO getMovieInfoDetail(String movieCd) throws Exception {
        MovieVO MovieInfo = movieMapper.getMovieInfoDetail(movieCd);

        // 장르
        if (!isEmpty(MovieInfo.getJanres())) {
            ArrayList<String> janreList = movieMapper.listJanres(MovieInfo.getJanres());
            String janres = "";

            for (int i=0; i<janreList.size() ; i++) {
                if (i > 0) {
                    janres += ", ";
                }
                janres += janreList.get(i);
            }
            MovieInfo.setJanres(janres);
        }

        // 캐릭터
        ArrayList<CharacterVO> characterList = movieMapper.listCharacter(movieCd);
        MovieInfo.setCharacterList(characterList);

        // 평균 별점
        String grade = movieMapper.getMovieGrade(movieCd);
        if (!isEmpty(grade)) {
            double tmp = Float.valueOf(grade) / 10.0;
            grade = String.valueOf(tmp * 10);
        }
        MovieInfo.setGrade(grade);

        return MovieInfo;
    }

    // 영화별 전체 점수 기록 업데이트
    @Override
    public Boolean updateMovieGradeReport(GradeReviewVO param, String oldGrade) throws Exception {
        int processResult = -1;

        Map pMap = new HashMap();
        pMap.put("movieCd", param.getMovieCd());

        // insert, 새로 점수 부여
        if (isEmpty(oldGrade)) {
            pMap.put("grade", "grade" + param.getGrade().replace(".", "_"));
            processResult = movieMapper.countUpMovieGradeReport(pMap);
        }
        // delete, 새로 준 점수가 0점 -> 삭제
        else if("0".equals(param.getGrade())) {
            pMap.put("grade", "grade" + oldGrade.replace(".", "_"));
            processResult = movieMapper.countDownMovieGradeReport(pMap);
        }
        // update 이전과 다른 점수 부여(같은 점수여도 업데이트)
        else {
            pMap.put("grade", "grade" + oldGrade.replace(".", "_"));
            processResult = movieMapper.countDownMovieGradeReport(pMap);

            if (processResult > 0 ? true : false) {
                pMap.put("grade", "grade" + param.getGrade().replace(".", "_"));
                processResult = movieMapper.countUpMovieGradeReport(pMap);
            }
        }

        return processResult > 0 ? true : false;
    }
}
