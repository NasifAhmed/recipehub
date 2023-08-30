package com.ahmed.recipehub.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.services.RecipeService;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private RecipeService recipeService;

    @Override
    public void run(String... args) throws Exception {

        List<Recipe> recipe = recipeService.getAll();
        
        if(recipe.size() == 0) {
            Recipe recipe1 = new Recipe();
            recipe1.setTitle("Title of recipe 1");
            recipe1.setBody("This is the body of recipe1");

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Title of recipe 2");
            recipe2.setBody("This is the body of recipe2");
            
            recipeService.save(recipe1);
            recipeService.save(recipe2);
        }
    }
    
     
}
