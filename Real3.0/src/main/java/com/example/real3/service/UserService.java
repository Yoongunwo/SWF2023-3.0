package com.example.real3.service;

import com.example.real3.admin.User;
import com.example.real3.form.UserLoginForm;
import com.example.real3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }
    public User findLoginMember(UserLoginForm userLoginForm) {
        User findUser = userRepository.findUserByUserId(userLoginForm.getEmail());

        if(userLoginForm.getPassword().equals(findUser.getPassWord())){
            return findUser;
        }

        return null;
    }
}
