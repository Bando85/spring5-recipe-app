package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by Andras Laczo 2020. 03. 03.
 */

@Service
public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand ingredientCommand);

    //void deleteByRecipeIdAndIngredientId(String valueOf, String valueOf1);

    Mono<Void> deleteById(String recipeId, String ingredientId);
}
