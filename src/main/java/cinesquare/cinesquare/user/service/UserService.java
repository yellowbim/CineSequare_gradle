package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.vo.UserVO;

public interface UserService {

    String signup(UserVO param) throws Exception;

    String apiSignup(UserVO param) throws Exception;

    boolean signupValid(String param) throws Exception;

    UserVO getUserInfo(UserVO param) throws Exception;

}
