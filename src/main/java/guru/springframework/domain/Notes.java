package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */

@Getter
@Setter
public class Notes {

    private String id;
    private String recipeNotes;
    private Recipe recipe;
}
