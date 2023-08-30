package com.ahmed.recipehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmed.recipehub.model.Recipe;
import com.ahmed.recipehub.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<Recipe> getById(Long id) {
        return recipeRepository.findById(id);
    }
    

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
