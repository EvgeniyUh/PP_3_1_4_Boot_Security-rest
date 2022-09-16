package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class InitClass {

    private final UserService userService;

    public InitClass(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initAdmin(){
        User user = new User("333", "333", "333", "333");
        Role role = new Role("ADMIN");
        role.setId(2);
        user.getRoles().add(role);
        userService.add(user);
    }
}
