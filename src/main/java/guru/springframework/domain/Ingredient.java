package guru.springframework.domain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Andras Laczo 2020. 01. 20.
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(String description, BigDecimal amount, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
    }


}
