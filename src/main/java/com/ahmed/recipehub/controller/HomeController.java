package com.ahmed.recipehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.services.RecipeService;

@Controller
public class HomeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Recipe> recipe = recipeService.getAll();
        model.addAttribute("recipes", recipe);

        return "home";
    }

}
