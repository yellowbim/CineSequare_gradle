package cinesquare.cinesquare.movie.service;

import cinesquare.cinesquare.common.vo.MovieVO;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<MovieVO> getMovieList(String param) throws Exception;

    List<Map> getBoxoffice() throws Exception;

}
