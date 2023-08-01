package com.example.real3.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserForm {

    private String name;
    private String userId;
    private String password;
    private Date birth;
    private boolean sex;
}