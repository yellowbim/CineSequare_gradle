package cinesquare.cinesquare.movie.service;

import cinesquare.cinesquare.common.vo.GradeReviewVO;
import cinesquare.cinesquare.common.vo.MovieVO;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<MovieVO> getMovieList(String param) throws Exception;

    List<Map> getBoxoffice() throws Exception;

    MovieVO getMovieInfoDetail(String param) throws Exception;

    Boolean updateMovieGradeReport(GradeReviewVO param, String oldGrade) throws Exception;
}
