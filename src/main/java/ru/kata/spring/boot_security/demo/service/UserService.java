package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    public void add(User user);

    public User getUser(Long id);

    public User getUserByName(String name);

    public void updateUser(User user);

    public void remove(long id);

    public List<User> listUsers();

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}
