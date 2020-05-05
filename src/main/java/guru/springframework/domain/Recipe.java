package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */

@Getter
@Setter
public class Recipe {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    private Difficulty difficulty;

    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    private Byte[] image;

    private Notes notes;

    private Set<Category> categories = new HashSet<Category>();

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

}
