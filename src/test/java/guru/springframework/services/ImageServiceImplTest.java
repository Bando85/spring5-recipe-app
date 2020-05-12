package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.controllers.ImageController;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Andras Laczo 2020. 04. 05.
 */

public class ImageServiceImplTest {

    @Mock
    RecipeReactiveRepository recipeRepository;

    @Mock
    RecipeService recipeService;

    ImageService imageService;

    MockMvc mockMvc;

    ImageController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception {
        //given
        String id = "1";
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain", "AndrasLaczo".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        //when

        imageService.saveImageFile(id, multipartFile).block();

        //then

        verify(recipeRepository).save(recipeArgumentCaptor.capture());
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        assertEquals(savedRecipe.getImage().length, multipartFile.getBytes().length);

    }

    @Test
    public void renderImageFromDB() throws Exception {
        //given
        String id = "1";
        String s = "fake image test";

        RecipeCommand command = new RecipeCommand();
        command.setId(id);
        Byte[] bytes = new Byte[s.getBytes().length];

        int i=0;

        for (byte b: s.getBytes()) {
            bytes[i++] = b;
        }

        command.setImage(bytes);

        when(recipeService.findCommandById(id)).thenReturn(Mono.just(command));

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        //then

        assertEquals(s.getBytes().length, responseBytes.length);

    }

}
