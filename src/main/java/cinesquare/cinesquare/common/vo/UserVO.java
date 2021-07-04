package cinesquare.cinesquare.common.vo;

import lombok.Data;

@Data
public class UserVO {

    private String token;
    private String account;
    private String password;
    private String name;
    private int showWatchTime;

}
