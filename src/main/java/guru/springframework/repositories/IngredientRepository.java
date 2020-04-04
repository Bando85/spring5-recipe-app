package guru.springframework.repositories;

import guru.springframework.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andras Laczo 2020. 04. 04.
 */

public interface IngredientRepository extends CrudRepository <Ingredient, Long> {

}
