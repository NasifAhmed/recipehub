package com.ahmed.recipehub.config;

import java.util.List;

import com.ahmed.recipehub.model.Account;
import com.ahmed.recipehub.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.services.RecipeService;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {

        List<Recipe> recipe = recipeService.getAll();
        
        if(recipe.isEmpty()) {

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setName("user");
            account1.setEmail("user@email.com");
            account1.setPassword("password");

            account2.setName("admin");
            account2.setEmail("admin@email.com");
            account2.setPassword("password");

            accountService.save(account1);
            accountService.save(account2);

            Recipe recipe1 = new Recipe();
            recipe1.setTitle("Title of recipe 1");
            recipe1.setBody("This is the body of recipe1");
            recipe1.setAccount(account1);

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Title of recipe 2");
            recipe2.setBody("This is the body of recipe2");
            recipe2.setAccount(account2);
            
            recipeService.save(recipe1);
            recipeService.save(recipe2);
        }
    }
    
     
}
