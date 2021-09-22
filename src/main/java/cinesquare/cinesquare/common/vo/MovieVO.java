package cinesquare.cinesquare.common.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MovieVO {

    private String movieCd;
    private String movieNm;
    private String openDt;
    private String janres;
    private String nations;
    private String showTm;
    private String grade;
    private String mainImg;
    private ArrayList<CharacterVO> characterList;

}
