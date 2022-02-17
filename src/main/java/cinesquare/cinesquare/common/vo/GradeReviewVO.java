package cinesquare.cinesquare.common.vo;

import lombok.Data;

@Data
public class GradeReviewVO {

    private String account;
    private String password;
    private int totalWatchTime;
    private int showTm;
    private String cineToken;
    private String movieCd;
    private String movieNm;
    private String grade;
    private String review;
}