package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){

        log.debug("This is a debug log in the Controller Class");
        model.addAttribute("recipes", recipeService.getRecipes());


        //Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        //Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        //System.out.println("Cat Id is:" + categoryOptional.get().getId());
        //System.out.println("UOM Id is" + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
