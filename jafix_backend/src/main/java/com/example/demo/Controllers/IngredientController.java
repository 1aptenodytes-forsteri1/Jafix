package com.example.demo.Controllers;

import com.example.demo.Models.Ingredient;
import com.example.demo.Repositories.IngredientRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    IngredientController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientRepository.getIngredients();
    }
}
