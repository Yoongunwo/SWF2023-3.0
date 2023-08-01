package com.example.real3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Slf4j
@Controller
public class homeController {
    @GetMapping("/")
    public String main() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmltcG9ydCBodHRwLmNsaWVudCAgY29ubiA9IGh0dHAuY2xpZW50LkhUVFBTQ29ubmVjdGlvbigid2FsbGV0LWFwaS5rbGF5dG5hcGkuY29tIikgIGhlYWRlcnMgPSB7ICAgICAneC1jaGFpbi1pZCc=")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        log.info(response.body());


        return "main";
    }
    @PostMapping("/")
    public String mainPost() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wallet-api.klaytnapi.com/v2/account"))
                .header("x-chain-id", "1001")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmltcG9ydCBodHRwLmNsaWVudCAgY29ubiA9IGh0dHAuY2xpZW50LkhUVFBTQ29ubmVjdGlvbigid2FsbGV0LWFwaS5rbGF5dG5hcGkuY29tIikgIGhlYWRlcnMgPSB7ICAgICAneC1jaGFpbi1pZCc=")
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return "test2";
    }


    @GetMapping("/step1")
    public String step1() {
        return "test2";
    }
    @GetMapping("/1")
    public String step2(){
        return "test3";
    }
    @GetMapping("/2")
    public String step3(){
        return "test3_1";
    }
}