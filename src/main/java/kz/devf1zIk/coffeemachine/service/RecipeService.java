package kz.devf1zIk.coffeemachine.service;

import kz.devf1zIk.coffeemachine.dto.RecipeDto;
import kz.devf1zIk.coffeemachine.model.Recipe;
import kz.devf1zIk.coffeemachine.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;

    public Recipe addRecipe(RecipeDto recipeDto) {
        var recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        return recipeRepository.save(recipe);
    }
}
