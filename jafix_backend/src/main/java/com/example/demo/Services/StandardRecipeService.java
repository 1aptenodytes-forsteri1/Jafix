package com.example.demo.Services;

import com.example.demo.Models.StandardRecipe;
import com.example.demo.Models.StandardRecipeComponent;
import com.example.demo.Repositories.StandardRecipeRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class StandardRecipeService {
    private final StandardRecipeRepository standardRecipeRepository;
    public StandardRecipeService(StandardRecipeRepository standardRecipeRepository){
        this.standardRecipeRepository = standardRecipeRepository;
    }
    public List<StandardRecipe>getStandardRecipes(){
        List<StandardRecipe> standardRecipes = new ArrayList<>();
        for (StandardRecipe standardRecipe : standardRecipeRepository.getAllRecipes()){
            File path = new File(standardRecipe.getImage());
            standardRecipe.setImage(path.getAbsolutePath());
            List<StandardRecipeComponent> standardRecipeComponents = standardRecipeRepository.getComponentsById(standardRecipe.getStandard_recipe_id());
            for (StandardRecipeComponent standardRecipeComponent : standardRecipeComponents){
                standardRecipe.addComponent(standardRecipeComponent.getIngredient(),standardRecipeComponent.getAmount());
            }
            standardRecipes.add(standardRecipe);
        }
        return standardRecipes;
    }
}
