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
    public String signup(UserVO user) throws IllegalAccessException {
        String rtnText = "success";
        try {
            mapper.signup(user);
        } catch (Exception e){
            rtnText = "fail";
        }
        return rtnText;
    }

    // api 회원가입 test
    @Override
    public String apiSignup(UserVO user) throws IllegalAccessException {
        String rtnText = "";
        try {
            mapper.apiSignup(user);
            rtnText = mapper.last();
        } catch (Exception e){
            rtnText = "fail";
        }
        return rtnText;
    }

    // 계정 중복확인
    @Override
    public boolean signupValid(String param) throws IllegalAccessException {
        boolean rtnText = false;
        if (mapper.validSignup(param) == 0) rtnText = true;

        return rtnText;
    }

    // 회원정보
    @Override
    public UserVO getUserInfo(UserVO user) throws IllegalAccessException {
        return mapper.getUserInfo(user);
    }
}
