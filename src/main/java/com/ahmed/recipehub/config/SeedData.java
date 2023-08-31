package com.ahmed.recipehub.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ahmed.recipehub.model.Account;
import com.ahmed.recipehub.model.Authority;
import com.ahmed.recipehub.repository.AuthorityRepository;
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

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Recipe> recipe = recipeService.getAll();
        
        if(recipe.isEmpty()) {

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();
            Account account3 = new Account();
            Account account4 = new Account();

            account1.setName("Abdul Hamid");
            account1.setEmail("hamid@email.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setName("admin");
            account2.setEmail("admin@email.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            account3.setName("Nasif Ahmed");
            account3.setEmail("nasif@email.com");
            account3.setPassword("password");
            Set<Authority> authorities3 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities3::add);
            account3.setAuthorities(authorities3);

            account4.setName("John Smith");
            account4.setEmail("john@email.com");
            account4.setPassword("password");
            Set<Authority> authorities4 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities4::add);
            account4.setAuthorities(authorities3);

            accountService.save(account1);
            accountService.save(account2);
            accountService.save(account3);
            accountService.save(account4);

            Recipe recipe1 = new Recipe();
            recipe1.setTitle("Biriyani");
            recipe1.setBody("Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.");
            recipe1.setAccount(account1);

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Burger");
            recipe2.setBody("Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.");
            recipe2.setAccount(account3);

            Recipe recipe3 = new Recipe();
            recipe3.setTitle("Pizza");
            recipe3.setBody("Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis. Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, velit rem. Dolor, omnis molestias? Exercitationem accusantium, magnam consequuntur ullam culpa aspernatur earum porro eligendi blanditiis minima perspiciatis. Fugit, dolorem quis.");
            recipe3.setAccount(account4);
            
            recipeService.save(recipe1);
            recipeService.save(recipe2);
            recipeService.save(recipe3);
        }
    }
    
     
}
