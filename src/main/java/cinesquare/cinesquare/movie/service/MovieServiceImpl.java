package cinesquare.cinesquare.movie.service;

import cinesquare.cinesquare.common.mapper.MovieMapper;
import cinesquare.cinesquare.common.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

}
