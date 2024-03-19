package com.sparta.collabobo.user.controller;

import com.sparta.collabobo.user.dto.request.LoginRequestDto;
import com.sparta.collabobo.user.dto.request.SignupRequestDto;
import com.sparta.collabobo.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  @GetMapping("/user/login-page")
  public String loginPage() {
    return "login";
  }

  @GetMapping("/user/signup")
  public String signupPage() {
    return "signup";
  }

  @PostMapping("/user/signup")
  public String singup(SignupRequestDto requestDto){
    userService.signup(requestDto);
    return "redirect:/api/user/login-page";
  }

}