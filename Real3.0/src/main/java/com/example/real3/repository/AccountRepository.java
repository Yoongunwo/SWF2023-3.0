package com.example.real3.repository;

import com.example.real3.admin.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String>{
}
