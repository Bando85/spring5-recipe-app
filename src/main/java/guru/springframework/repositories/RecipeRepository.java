package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andras Laczo 2020. 01. 21.
 */

public interface RecipeRepository extends CrudRepository<Recipe, String> {


}
