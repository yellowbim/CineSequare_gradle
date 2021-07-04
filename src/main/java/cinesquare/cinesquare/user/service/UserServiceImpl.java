package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.mapper.UserMapper;
import cinesquare.cinesquare.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    // CINE 회원가입
    @Override
    public String signup(UserVO param) throws IllegalAccessException {
        String rtnText = "success";
        try {
            mapper.signup(param);
        } catch (Exception e){
            rtnText = "fail";
        }
        return rtnText;
    }

    // 계정 중복확인
    @Override
    public boolean validAccount(String param) throws IllegalAccessException {
        boolean rtnText = false;
        if (mapper.validAccount(param) == 0) rtnText = true;

        return rtnText;
    }

    // CINE
    @Override
    public UserVO getUser(UserVO param) throws IllegalAccessException {
        return mapper.getUser(param);
    }
}
