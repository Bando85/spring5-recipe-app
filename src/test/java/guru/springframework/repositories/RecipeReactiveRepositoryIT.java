package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.reactive.CategoryReactiveRepository;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository reactiveRepository;

    @Before
    public void setUp() throws Exception {

/*
        DevBootstrap devBootstrap = new DevBootstrap(categoryRepository,recipeRepository,unitOfMeasureRepository);
        devBootstrap.onApplicationEvent(null);*/
    }

    @Test
    public void saveDocument() {
        Recipe recipe = new Recipe();
        recipe.setDescription("Recipe description");
        Recipe savedRecipe = reactiveRepository.save(recipe).block();

        Recipe getRecipe = reactiveRepository.findById(savedRecipe.getId()).block();
        Long count = reactiveRepository.count().block();

        assertEquals(savedRecipe.getId(), getRecipe.getId());
        assertEquals(Long.valueOf(1L), count);
    }

}
