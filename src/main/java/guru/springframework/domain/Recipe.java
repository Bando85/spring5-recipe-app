package guru.springframework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difiiculty difiiculty;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<Ingredient>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name= "category_id"))
    private Set<Category> categories = new HashSet<Category>();


    public void addIngredient(BigDecimal amount, String desc){
        this.getIngredients().add(new Ingredient(desc, amount, this));
    }

}
