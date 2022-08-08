package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserDao userDao;

    public UserServiceImp(UserDao ud) {
        this.userDao = ud;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Transactional(readOnly=true)
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional(readOnly=true)
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public void remove(long id) {
        userDao.remove(id);
    }

    @Transactional(readOnly=true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.initUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Transactional(readOnly=true)
    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }
}
