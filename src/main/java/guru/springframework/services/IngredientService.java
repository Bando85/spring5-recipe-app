package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import org.springframework.stereotype.Service;

/**
 * Created by Andras Laczo 2020. 03. 03.
 */

@Service
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

}
