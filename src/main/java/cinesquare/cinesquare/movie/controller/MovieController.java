package cinesquare.cinesquare.movie.controller;

import cinesquare.cinesquare.common.vo.MovieVO;
import cinesquare.cinesquare.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public List<MovieVO> getMovie(@RequestParam String searchWord) throws Exception {
//        List<MovieVO> movieList = movieService.getMovieList(searchWord);
//
//        return movieList;
//    }
}