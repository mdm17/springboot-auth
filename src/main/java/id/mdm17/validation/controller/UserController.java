package id.mdm17.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.mdm17.validation.entity.User;
import id.mdm17.validation.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/profile")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping
    public User getUser() {
        return userServiceImpl.loadUserByCredential();
    }
}
