package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */

@Getter
@Setter
public class Notes {

    @Id
    private String id;
    private String recipeNotes;
    private Recipe recipe;
}
