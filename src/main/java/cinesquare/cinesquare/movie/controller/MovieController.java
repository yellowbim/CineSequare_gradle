package cinesquare.cinesquare.movie.controller;

import cinesquare.cinesquare.common.vo.MovieVO;
import cinesquare.cinesquare.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map getMovie(@RequestParam String searchWord) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MovieVO> movieList = movieService.getMovieList(searchWord);
        resultMap.put("result", movieList);

        return resultMap;
    }

    @RequestMapping(value = "/boxoffice", method = RequestMethod.GET)
    public Map getMovie() throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map> movieList = movieService.getBoxoffice();
        resultMap.put("result", movieList);

        return resultMap;
    }
}