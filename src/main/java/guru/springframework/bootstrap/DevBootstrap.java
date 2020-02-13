package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difiiculty;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashSet;

/**
 * Created by Andras Laczo 2020. 01. 25.
 */

@Slf4j
@Component
public class DevBootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DevBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            loadData();
    }

    private void loadData() {

        log.debug("This is a debug log in Bootstrap.loadData method");

        //setting up Categories
        HashSet<Category> catSet1 = new HashSet<Category>();
        catSet1.add(categoryRepository.findByDescription("Mexican").get());


        //setting up recipes

        Recipe sgcTacos = new Recipe();
        sgcTacos.setCategories(catSet1);
        sgcTacos.setCookTime(15);
        sgcTacos.setPrepTime(20);
        sgcTacos.setDifiiculty(Difiiculty.MODERATE);
        sgcTacos.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        sgcTacos.setServings(4);
        sgcTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        sgcTacos.setSource("by Sally Vargas");
        sgcTacos.setNotes(new Notes("Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "\n" +
                "\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "Spicy Grilled Chicken TacosThe ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n" +
                "\n" +
                "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n" +
                "\n" +
                "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                "\n" +
                "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!"));

        //setting up ingredients

        sgcTacos.addIngredient (new BigDecimal(1), "dried oregano");


        //setting up image
        String imagePath = "C:\\Users\\laczo\\IdeaProjects\\spring5-recipe-app\\src\\main\\resources\\images\\taco.jpg";
        try {
            File myPicture = new File(imagePath);
            byte[] arr1;
            arr1=Files.readAllBytes(myPicture.toPath());
            Byte[] arr2;


            //sgcTacos.setImage();

        } catch (IOException e) {
            e.printStackTrace();
        }

        recipeRepository.save(sgcTacos);


    }
}

