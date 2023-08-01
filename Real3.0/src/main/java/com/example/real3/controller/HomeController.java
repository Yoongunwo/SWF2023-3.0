package com.example.real3.controller;

import com.example.real3.admin.User;
import com.example.real3.form.UserForm;
import com.example.real3.form.UserLoginForm;
import com.example.real3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String main(Model model) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "8217")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmltcG9ydCBodHRwLmNsaWVudCAgY29ubiA9IGh0dHAuY2xpZW50LkhUVFBTQ29ubmVjdGlvbigid2FsbGV0LWFwaS5rbGF5dG5hcGkuY29tIikgIGhlYWRlcnMgPSB7ICAgICAneC1jaGFpbi1pZCc=")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        log.info(response.body());
        model.addAttribute("userForm", new UserForm());

        return "main";
    }

    @PostMapping("/")
    public String mainPost(UserForm userForm, HttpSession session) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "8217")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmJ3SW9IRlVVZWdWOXpGRDU1TWVPRTNkWVBVZHMwTzB4WFh2MThBTjQ=")
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());


        User user = new User();
        user.setName(userForm.getName());
        user.setUserId(userForm.getUserId());
        user.setPassWord(userForm.getPassword());
//        user.setSex(userForm.isSex());
//        user.setBirth(userForm.getBirth());
        userService.saveUser(user);
        log.info("why?");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login_check(Model model) {
        model.addAttribute("memberLoginForm", new UserLoginForm());
        return "login";
    }

    @PostMapping("/members/login")
    public String login(UserLoginForm userLoginForm, BindingResult result, Model model, HttpSession session) {

        User loginUser = userService.findLoginMember(userLoginForm);

        if (loginUser == null){
            return "redirect:/";
        }
        session.setAttribute("user", loginUser);

        return "main";
    }
}