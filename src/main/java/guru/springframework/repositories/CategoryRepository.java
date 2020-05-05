package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Andras Laczo 2020. 01. 21.
 */

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);

}
