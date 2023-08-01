package com.example.real3.admin;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private String name;
    private String userId;
    private String passWord;
    private String address;
    private String privateKey;
    private Date birth;
    private Boolean sex;
}
