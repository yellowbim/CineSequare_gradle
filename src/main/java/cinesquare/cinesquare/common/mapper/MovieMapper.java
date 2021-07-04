package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper {

    // 영화 검색
    List<MovieVO> searchMovie(String param);
}
