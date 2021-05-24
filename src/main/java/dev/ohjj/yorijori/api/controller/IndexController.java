package dev.ohjj.yorijori.api.controller;

import dev.ohjj.yorijori.api.persistence.member.UserService;
import dev.ohjj.yorijori.api.persistence.member.entity.UserEntity;
import dev.ohjj.yorijori.api.persistence.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping({"", "/"})
    public String index() {
        return "index"; // src/main/resources/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody
    String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    // 스프링시큐리티 해당주소를 낚아채버린다. - SecurityConfig 파일 생성 후 작동안함
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 스프링시큐리티 해당주소를 낚아채버린다. - SecurityConfig 파일 생성 후 작동안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(UserEntity userEntity) {
        userService.join(userEntity);

        return "redirect:/loginForm";
    }
}
