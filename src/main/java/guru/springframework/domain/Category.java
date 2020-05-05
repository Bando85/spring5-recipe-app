package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * Created by Andras Laczo 2020. 01. 21.
 */

@Getter
@Setter
public class Category {

    private String id;
    private String description;

    private Set<Recipe> recipes;


}
