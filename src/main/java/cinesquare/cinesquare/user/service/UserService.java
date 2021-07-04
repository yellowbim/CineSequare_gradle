package cinesquare.cinesquare.user.service;

import cinesquare.cinesquare.common.vo.UserVO;

public interface UserService {

    public String signup(UserVO param) throws Exception;

    public boolean validAccount(String param) throws Exception;

    public UserVO getUser(UserVO param) throws Exception;

}
