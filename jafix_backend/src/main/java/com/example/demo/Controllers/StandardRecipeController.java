package com.example.demo.Controllers;

import com.example.demo.Models.StandardRecipe;
import com.example.demo.Services.StandardRecipeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/standard_recipes")
public class StandardRecipeController {
    private final StandardRecipeService standardRecipeService;
    StandardRecipeController(StandardRecipeService standardRecipeService){
        this.standardRecipeService = standardRecipeService;
    }
    @GetMapping
    public List<StandardRecipe> getRecipes(){
        return standardRecipeService.getStandardRecipes();
    }
}
