package com.ahmed.recipehub.controller;

import com.ahmed.recipehub.model.Account;
import com.ahmed.recipehub.model.Authority;
import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.repository.RecipeRepository;
import com.ahmed.recipehub.services.AccountService;
import com.ahmed.recipehub.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/recipe/{id}")
    public String getRecipe(@PathVariable Long id, Model model, Principal principal) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            Account author = recipe.getAccount();
            String emailFromDB = author.getEmail();
            String emailFromSession = principal.getName();
            Boolean userCheck = emailFromSession.equals(emailFromDB);
            Optional<Account> optionalAccount = accountService.findByEmail(emailFromSession);

            Set<Authority> accountAuthorities = optionalAccount.get().getAuthorities();

            boolean isAdmin = accountAuthorities.stream()
                    .anyMatch(authority -> "ROLE_ADMIN".equals(authority.getName()));

            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("userCheck", userCheck);

            return "recipe-with-control";
               
        } else {
            return "404";
        }
    }

    @PostMapping("/recipe/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updateRecipe(@PathVariable Long id, Recipe recipe, BindingResult result, Model model) {

        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();

            existingRecipe.setTitle(recipe.getTitle());
            existingRecipe.setBody(recipe.getBody());

            recipeService.save(existingRecipe);
        }

        return "redirect:/recipe/" + recipe.getId();
    }

    @GetMapping("/recipe/new")
    @PreAuthorize("isAuthenticated()")
    public String createNewRecipe(Model model, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }

        Optional<Account> optionalAccount = accountService.findByEmail(authUsername);
        if(optionalAccount.isPresent()) {
            Recipe recipe = new Recipe();
            recipe.setAccount(optionalAccount.get());
            model.addAttribute("recipe", recipe);
            return "recipe_new";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/recipe/new")
    @PreAuthorize("isAuthenticated()")
    public String saveNewRecipe(@ModelAttribute Recipe recipe, Principal principal) {

        String authUsername = "anonymousUser";
        if (principal != null) {
            authUsername = principal.getName();
        }
        if (recipe.getAccount().getEmail().compareToIgnoreCase(authUsername) < 0) {
            // Error for not matching the current poster email with logged in email. Handle with error.
        }
        recipeService.save(recipe);
        return "redirect:/recipe/" + recipe.getId();
    }

    @GetMapping("/recipe/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getRecipeForEdit(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        // if post exist put it in model
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe_edit";
        } else {
            return "404";
        }
    }

    @GetMapping("/recipe/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRecipe(@PathVariable Long id) {

        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            recipeService.delete(recipe);
            return "redirect:/";
        } else {
            return "404-notadmin";
        }
    }
}
