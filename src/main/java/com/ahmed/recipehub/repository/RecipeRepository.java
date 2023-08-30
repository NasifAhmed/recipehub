package com.ahmed.recipehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmed.recipehub.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}
