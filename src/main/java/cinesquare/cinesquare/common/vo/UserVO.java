package cinesquare.cinesquare.common.vo;

import lombok.Data;

@Data
public class UserVO {

    private String cineToken;
    private String apiToken;
    private String account;
    private String password;
    private String name;
    private int showWatchTime;

}
