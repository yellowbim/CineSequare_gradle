package cinesquare.cinesquare.common.vo;

import lombok.Data;

@Data
public class UserContentsVO<T> extends UserVO{
   private T data;
}