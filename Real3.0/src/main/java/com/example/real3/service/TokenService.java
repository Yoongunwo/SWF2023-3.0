package com.example.real3.service;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class TokenService {
    public void makeToken(String address, String id, String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://kip17-api.klaytnapi.com/v2/contract/0xef9864db8494194b0e8a38280a71f74ef2d82065/token"))
                .header("x-chain-id", "1001")
                .header("x-krn", "")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic S0FTSzBQNFlMRDc3UjJLWDI4REFQWFRYOmJ3SW9IRlVVZWdWOXpGRDU1TWVPRTNkWVBVZHMwTzB4WFh2MThBTjQ=")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\n  "
                                                                                    + "\"to\": \"" + address + "\",\n  "
                                                                                    + "\"id\": \"" + id + "\",\n  "
                                                                                    + "\"uri\": \"" + uri + "\"\n"
                                                                                    + "}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info(response.body());
    }
}
