package cinesquare.cinesquare.movie.controller;

import cinesquare.cinesquare.common.vo.MovieVO;
import cinesquare.cinesquare.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://cinesquare.yahmedora.com:8088", "http://54.180.29.206:8088", "https://graceful-starburst-2727fb.netlify.app", "https://cinesquare.slowtuttle.co.kr:8088", "http://cinesquare.slowtuttle.co.kr:8088"})
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // 영화 검색
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map getMovie(@RequestParam String searchWord) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MovieVO> movieList = movieService.getMovieList(searchWord);
        resultMap.put("result", movieList);

        return resultMap;
    }

    // 박스오피스
    @RequestMapping(value = "/boxoffice", method = RequestMethod.GET)
    public Map getBoxoffice() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> movieList = movieService.getBoxoffice();
        resultMap.put("result", movieList);

        return resultMap;
    }

    // 영화 정보
    @RequestMapping(value = "/movieInfo", method = RequestMethod.GET)
    public Map getMovieInfoDetail(@RequestParam String movieCd) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        MovieVO movieInfo = movieService.getMovieInfoDetail(movieCd);
        resultMap.put("result", movieInfo);

        return resultMap;
    }
}