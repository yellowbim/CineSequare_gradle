package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.MovieVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MovieMapper {

    List<MovieVO> searchMovie(String param) throws IllegalAccessException;

    List<Map> getBoxoffice() throws IllegalAccessException;
}
