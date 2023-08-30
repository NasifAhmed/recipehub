package com.ahmed.recipehub.controller;

import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipe/{id}")
    public String getRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "404";
        }
    }
}
