package com.ahmed.recipehub.controller;

import com.ahmed.recipehub.model.Account;
import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

}
