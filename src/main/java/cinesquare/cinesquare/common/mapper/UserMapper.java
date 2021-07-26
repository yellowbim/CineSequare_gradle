package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int signup(UserVO param) throws IllegalAccessException;

    int apiSignup(UserVO param) throws IllegalAccessException;

    String last() throws IllegalAccessException;

    int validSignup(String param) throws IllegalAccessException;

    UserVO getUserInfo(UserVO param) throws IllegalAccessException;
}
