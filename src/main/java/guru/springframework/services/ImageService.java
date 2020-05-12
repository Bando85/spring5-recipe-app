package guru.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * Created by Andras Laczo 2020. 04. 04.
 */

@Service
public interface ImageService {
   Mono<Void> saveImageFile(String recipeId, MultipartFile File);
}
