package com.example.real3.controller;

import com.example.real3.admin.Account;
import com.example.real3.admin.User;
import com.example.real3.form.UserForm;
import com.example.real3.form.UserLoginForm;
import com.example.real3.service.JsonService;
import com.example.real3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final JsonService jsonService;

    @GetMapping("/zero")
    public String zero(){
        return "zero";
    }
    @GetMapping("/register")
    public String main(Model model) throws IOException, InterruptedException {
        /*HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "8217")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmltcG9ydCBodHRwLmNsaWVudCAgY29ubiA9IGh0dHAuY2xpZW50LkhUVFBTQ29ubmVjdGlvbigid2FsbGV0LWFwaS5rbGF5dG5hcGkuY29tIikgIGhlYWRlcnMgPSB7ICAgICAneC1jaGFpbi1pZCc=")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
        */
        model.addAttribute("userForm", new UserForm());

        return "/start_form/signup";
    }

    @PostMapping("/register")
    public String mainPost(UserForm userForm, HttpSession session) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "1001")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTS1pHMkZNMDUzREZQNTlDVFJSSFpHOmFZZHVVSmZYV1FnSlBLZUhyV1o3Um5LVUhodkpDLTB2d2JyeU5Md3Y=")
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
        Account account = jsonService.split(response.body());

        User user = new User(userForm.getName(),
                            userForm.getUserId(),
                            userForm.getPassword(),
                            userForm.getBirth(),
                            userForm.getSex(),
                            account);

        userService.saveUser(user, account);
        log.info("why?");
        return "redirect:http://localhost:8080/";
    }

    @GetMapping("/")
    public String login_check(Model model) {
        log.info("hi1");
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "/start_form/login";
    }

    @PostMapping("/")
    public String login(UserLoginForm userLoginForm, BindingResult result, HttpSession session) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "1001")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTS1pHMkZNMDUzREZQNTlDVFJSSFpHOmFZZHVVSmZYV1FnSlBLZUhyV1o3Um5LVUhodkpDLTB2d2JyeU5Md3Y=")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        log.info("hi2");
        User loginUser = userService.findLoginMember(userLoginForm);

        if (loginUser == null){
            log.info("fail");
            return "redirect:/";
        }
        session.setAttribute("user", loginUser);
        session.setAttribute("account", loginUser.getAccount());
        log.info("pass");
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String getHome(){
        return "/main_form/main_view";
    }
    @GetMapping("/market")
    public String getMarket(){
        return "/seed_form/p_market";
    }
    @GetMapping("/bookmark")
    public String getBookMark(){
        return "/seed_form/detail_mark";
    }

    @GetMapping("/page")
    public String getPage(){
        return "/page_form/page";
    }
    @GetMapping("/mypage")
    public String getMyPage(){
        return "/mypage/mypage";
    }

    @GetMapping("/search")
    public String getSearchPage(){
        return "/main_form/search_view";
    }
    @GetMapping("/wallet")
    public String getWallet(){
        return "/wallet";
    }
    @GetMapping("/buy")
    public String getBuy(){
        return "/buy1";
    }
    @GetMapping("/detailMark")
    public String getDetailMark(){
        return "seed_form/detail_mark";
    }
}