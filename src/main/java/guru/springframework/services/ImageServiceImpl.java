package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * Created by Andras Laczo 2020. 04. 04.
 */

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        log.debug("SaveImageFile method invoked");
        try {
            Recipe recipe = recipeRepository.findById(recipeId).block();

            Byte[] bytes = new Byte[file.getBytes().length];

            int i=0;

            for (byte b: file.getBytes()) {
                bytes[i++] = b;
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe).block();

        } catch (Exception e) {
            log.error("Error in saveImageFile", e);
            e.printStackTrace();
        }
        return Mono.empty();
    }
}
