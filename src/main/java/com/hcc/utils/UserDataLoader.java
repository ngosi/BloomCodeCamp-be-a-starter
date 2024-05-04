package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello from the dat loader!");
        loadUserData();
        loadAuthorityData();
    }

    private void loadUserData() {
        if (userRepository.count() < 2) {
            PasswordEncoder pwenc = new BCryptPasswordEncoder();
            String pw = pwenc.encode("asdfasdf");

            User learner = new User(LocalDate.now(), "learner1", pw);
            User reviewer = new User(LocalDate.now(), "reviewer1", pw);
            userRepository.save(learner);
            userRepository.save(reviewer);
        }
    }

    private void loadAuthorityData() {
        if (authorityRepository.count() < 2) {
            Authority learner = new Authority("ROLE_LEARNER", userRepository.findByUsername("learner1").get());
            Authority reviewer = new Authority("ROLE_REVIEWER", userRepository.findByUsername("reviewer1").get());

            authorityRepository.save(learner);
            authorityRepository.save(reviewer);
        }
    }
}
