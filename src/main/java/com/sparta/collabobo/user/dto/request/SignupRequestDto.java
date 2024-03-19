package com.sparta.collabobo.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
  private String username;
  private String password;
  private String email;
  private String nickname;
  private boolean admin = false;
  private String adminToken = "";
}