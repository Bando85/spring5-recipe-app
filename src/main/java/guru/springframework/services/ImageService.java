package guru.springframework.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Andras Laczo 2020. 04. 04.
 */

@Service
public interface ImageService {
   void saveImageFile(String recipeId, MultipartFile File);
}
