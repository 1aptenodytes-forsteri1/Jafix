package com.example.demo.Controllers;

import com.example.demo.Models.CustomRecipe;
import com.example.demo.Repositories.CustomRecipeRepository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/custom_recipes")
public class CustomRecipeController {
    private final CustomRecipeRepository customRecipeRepository;
    CustomRecipeController(CustomRecipeRepository customRecipeRepository){
        this.customRecipeRepository = customRecipeRepository;
    }
    @PostMapping
    public void addRecipe(@RequestBody CustomRecipe customRecipe){
        customRecipeRepository.addRecipe(customRecipe);
    }
}
