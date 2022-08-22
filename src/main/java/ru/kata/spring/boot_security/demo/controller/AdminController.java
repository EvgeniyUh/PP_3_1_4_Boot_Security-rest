package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("BDroles", roleService.getAllRoles());
        return "admin/index";
    }

    @PostMapping()
    public String create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                         @RequestParam("age") String age, @RequestParam("email") String email,
                         @RequestParam("password") String password, @RequestParam("selectedRoles") String[] roles) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setPassword(password);
        user.setEmail(email);
        for (String name: roles) {
            user.getRoles().add(roleService.getRoleByName(name));
        }
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("BDroles", roleService.getAllRoles());
        return "admin/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("selectedRoles") String[] roles) {
        List<Role> newList = new ArrayList<>();
        for (String name: roles) {
            newList.add(roleService.getRoleByName(name));
        }
        user.setRoles(newList);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.remove(id);
        return "redirect:/admin";
    }

    @ModelAttribute("CurrentUser")
    public User addUser(Principal principal) {
        User user = null;
        if (principal != null) {
            user = userService.getUserByName(principal.getName());
        }
        return user;
    }
}
