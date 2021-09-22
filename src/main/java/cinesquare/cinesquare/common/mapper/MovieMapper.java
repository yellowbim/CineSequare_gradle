package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.CharacterVO;
import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MovieMapper {

    MovieVO getSimpleMovieInfo(String param) throws IllegalAccessException;

    List<MovieVO> searchMovie(String param) throws IllegalAccessException;

    List<Map> getBoxoffice() throws IllegalAccessException;

    MovieVO getMovieInfoDetail(String param) throws IllegalAccessException;

    ArrayList<String> listJanres(String param) throws IllegalAccessException;

    ArrayList<CharacterVO> listCharacter(String param) throws IllegalAccessException;

    String getMovieGrade(String param) throws IllegalAccessException;

    List<Map> checkMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int insertMovieGrade(GradeReviewVO param) throws IllegalAccessException;

    int updateMovieGrade(Map param) throws IllegalAccessException;
}
