package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public void add(User user);

    public User getUser(Long id);

    public User getUserByName(String name);

    public void updateUser(User user);

    public void remove(long id);

    public List<User> listUsers();

    public User initUser(String name);
}