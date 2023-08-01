package com.example.real3.service;

import com.example.real3.admin.Account;
import com.example.real3.admin.User;
import com.example.real3.form.UserLoginForm;
import com.example.real3.repository.AccountRepository;
import com.example.real3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public void saveUser(User user, Account account){

        userRepository.save(user);
        accountRepository.save(account);
    }
    public User findLoginMember(UserLoginForm userLoginForm) {
        log.info("1");
        User findUser = userRepository.findUserByUserId(userLoginForm.getUserId());
        if(findUser != null && userLoginForm.getPassword().equals(findUser.getPassword())){
            return findUser;
        }
//        log.info(findUser.getUserId());

        return null;
    }
}
