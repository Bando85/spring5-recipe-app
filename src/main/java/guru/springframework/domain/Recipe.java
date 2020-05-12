package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */

@Getter
@Setter
@Document
public class Recipe {

    @Id
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
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
        }
    }

}
