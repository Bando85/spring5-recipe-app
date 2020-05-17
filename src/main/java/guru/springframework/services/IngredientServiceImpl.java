package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import guru.springframework.repositories.reactive.UnitOfMeasureReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeReactiveRepository recipeRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureRepository;
    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 RecipeReactiveRepository recipeRepository, UnitOfMeasureReactiveRepository unitOfMeasureRepository, UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }

    @Override
    public Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        return recipeRepository
                .findById(recipeId)
                .flatMapIterable(Recipe::getIngredients)
                .filter(f -> f.getId().equalsIgnoreCase(ingredientId))
                .single()
                .map(ingredient -> {
                    IngredientCommand command = ingredientToIngredientCommand.convert(ingredient);
                    command.setRecipeId(recipeId);
                    return command;
                });
    }

    @Override
    @Transactional
    public Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command) {

        Mono<Recipe> savedRecipe = recipeRepository
                .findById(command.getRecipeId())
                .map(recipe -> {
                    Ingredient ingredient = recipe.getIngredients().stream()
                            .filter(i -> i.getId().equalsIgnoreCase(command.getId()))
                            .findFirst()
                            .get();
                    ingredient.setDescription(command.getDescription());
                    ingredient.setAmount(command.getAmount());
                    UnitOfMeasure uom = unitOfMeasureRepository.findById(command.getUnitOfMeasure().getId()).block();
                    ingredient.setUnitOfMeasure(uom);
                    return recipeRepository.save(recipe).block();
                });

        return savedRecipe
                .flatMapIterable(Recipe::getIngredients)
                .filter(i -> i.getId().equalsIgnoreCase(command.getId()))
                .single()
                .map(ingredient -> {
                    IngredientCommand commandFound = ingredientToIngredientCommand.convert(ingredient);
                    commandFound.setRecipeId(command.getRecipeId());
                    return commandFound;
                });
    }

        /* @Override
    public void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId).blockOptional();

        if (!recipeOptional.isPresent()) {
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();
        Set<Ingredient> ingredientSet = recipe.getIngredients();

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            recipe.getIngredients().remove(ingredientOptional.get());
            recipeRepository.save(recipe).block();
        } else {
            //throw error if target ingredient not present
            //todo impl error handling
            log.error("ingredient id not found. Id: " + ingredientId);
        }


    }*/

    @Override
    public Mono<Void> deleteById(String recipeId, String idToDelete) {

        log.debug("Deleting ingredient: " + recipeId + ":" + idToDelete);

        Recipe recipe = recipeRepository.findById(recipeId).block();

        if (recipe != null) {

            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                log.debug("found Ingredient");

                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe).block();
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }

        return Mono.empty();

    }
}