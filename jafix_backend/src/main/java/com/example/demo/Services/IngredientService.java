package com.example.demo.Services;

import com.example.demo.Models.Ingredient;
import com.example.demo.Repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    public List<Ingredient> ingredientsForRecipes(){
        List<Ingredient> result = ingredientRepository.getIngredients();
        for (Ingredient ingredient: result){
            if(ingredient.getUnits().equals("г")){
                ingredient.setUnits("мл");
                ingredient.setPrice((double) Math.round(ingredient.getPrice()*7/40*1000)/1000);
            }
        }
        return result;
    }
}
