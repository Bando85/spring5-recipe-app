package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import org.springframework.stereotype.Service;

/**
 * Created by Andras Laczo 2020. 03. 03.
 */

@Service
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteByRecipeIdAndIngredientId(String valueOf, String valueOf1);
}
