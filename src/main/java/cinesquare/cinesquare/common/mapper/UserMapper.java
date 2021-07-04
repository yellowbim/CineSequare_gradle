package cinesquare.cinesquare.common.mapper;

import cinesquare.cinesquare.common.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    int signup(UserVO param) throws IllegalAccessException;

    int validAccount(String param) throws IllegalAccessException;

    UserVO getUser(UserVO param) throws IllegalAccessException;
}
