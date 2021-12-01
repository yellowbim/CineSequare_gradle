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
    private MovieMapper mapper;

    // 영화 목록
    @Override
    public List<MovieVO> getMovieList(String param) throws Exception {
        List<MovieVO> movieList = mapper.searchMovie(param);

        return movieList;
    }

    // 박스오피스 목록
    @Override
    public List<Map> getBoxoffice() throws Exception {
        List<Map> movieList = mapper.getBoxoffice();

        return movieList;
    }

    // 영화 상세 정보
    @Override
    public MovieVO getMovieInfoDetail(String movieCd) throws Exception {
        MovieVO MovieInfo = mapper.getMovieInfoDetail(movieCd);

        // 장르
        if (!isEmpty(MovieInfo.getJanres())) {
            ArrayList<String> janreList = mapper.listJanres(MovieInfo.getJanres());
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
        ArrayList<CharacterVO> characterList = mapper.listCharacter(movieCd);
        MovieInfo.setCharacterList(characterList);

        // 평균 별점
        String grade = mapper.getMovieGrade(movieCd);
        if (!isEmpty(grade)) {
            double tmp = Float.valueOf(grade) / 10.0;
            grade = String.valueOf(tmp * 10);
        }
        MovieInfo.setGrade(grade);

        return MovieInfo;
    }

    // 영화 별점 주기
    // TODO 참고
    @Override
    public int updateMovieGrade(GradeReviewVO param, String oldCheck) throws Exception {
        Map<String, String> tMap = new HashMap<>();
        tMap.put("movieCd", param.getMovieCd());
        int oldResult = 1;
        int newResult = 0;

        if (!isEmpty(oldCheck)) {
            tMap.put("grade", "grade" + oldCheck.replace(".", "_"));
            oldResult = mapper.countUpMovieGrade(tMap);
        }

        if (!isEmpty(param.getGrade())) {
            tMap.put("grade", "grade" + param.getGrade().replace(".", "_"));
            newResult = mapper.countUpMovieGrade(tMap);
        } else {
            tMap.put("grade", "grade" + oldCheck.replace(".", "_"));
            newResult = mapper.countDownMovieGrade(tMap);
        }

        return oldResult > 0 && newResult > 0 ? 1 : -1;
    }
}
