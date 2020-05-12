package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {

}
