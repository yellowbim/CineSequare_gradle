package cinesquare.cinesquare.movie.service;

import cinesquare.cinesquare.common.vo.MovieVO;

import java.util.List;

public interface MovieService {

    List<MovieVO> getMovieList(String param) throws Exception;

}
