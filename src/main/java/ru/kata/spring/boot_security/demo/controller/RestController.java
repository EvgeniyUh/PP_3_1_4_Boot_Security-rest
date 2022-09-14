package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    private final UserService userService;
    private final RoleService roleService;

    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    private User prepareUser(User rawUser) {
//        User preparedUser = new User();
//        preparedUser.setFirstName(rawUser.getFirstName());
//        preparedUser.setLastName(rawUser.getLastName());
//        preparedUser.setAge(rawUser.getAge());
//        preparedUser.setEmail(rawUser.getEmail());
//        preparedUser.setId(rawUser.getId());
//        for (Role role : rawUser.getRoles()) {
//            preparedUser.getRoles().add(new Role(role.getName()));
//        }
//        return preparedUser;
//    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User rawUser = userService.getUser(id);
        return new ResponseEntity<>(rawUser, HttpStatus.OK);//prepareUser(rawUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
//        List<User> preparedUsers = new ArrayList<>();
//        for (User user : userService.listUsers()) {
//            preparedUsers.add(user);//prepareUser(user));
//        }
//        return preparedUsers;
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
//        List<Role> newRoleList = new ArrayList<>();
//        for (Role role: user.getRoles()) {
//            newRoleList.add(roleService.getRoleByName(role.getName()));
//        }
//        user.setRoles(newRoleList);
        userService.add(user);
    }

    @PutMapping("/edit") //PUT
    public void eitUser(@RequestBody User user) {
//        List<Role> newRoleList = new ArrayList<>();
//        for (Role role: user.getRoles()) {
//            newRoleList.add(roleService.getRoleByName(role.getName()));
//        }
//        user.setRoles(newRoleList);
        userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}") //DELETE
    public void delete(@PathVariable("id") long id) {
        userService.remove(id);
    }

}