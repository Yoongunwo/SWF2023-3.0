package com.example.real3.service;

import com.example.real3.admin.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonService {
    public Account split(String json) throws JsonProcessingException {
        String[] splits = json.split(",");
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 데이터를 Java 객체로 파싱
        Account data = objectMapper.readValue(json, Account.class);
        return data;
    }
}
