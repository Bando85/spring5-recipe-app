package guru.springframework.services;

import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Andras Laczo 2020. 01. 26.
 */

@Service
public interface RecipeService {

    Set<Recipe> getRecipes();

}
