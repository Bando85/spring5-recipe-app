package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeReactiveRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        //Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull("Null recipe returned", recipeReturned);
        assertEquals(recipe.getId(), recipeReturned.getId());
        verify(recipeRepository).findById(anyString());
        verify(recipeRepository, never()).findAll();

    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception{

        //Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(Mono.empty());

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNull(recipeReturned);

    }

    @Test
    public void getRecipesTest() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<Recipe>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(Flux.fromIterable(recipesData));

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(recipes.size(),1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        //given

        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeRepository.deleteById(anyString())).thenReturn(Mono.empty());

        //delete
        recipeService.deleteById(recipe.getId()).block();

        //then
        verify(recipeRepository).deleteById("1");

    }

}