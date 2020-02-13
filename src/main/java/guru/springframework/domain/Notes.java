package guru.springframework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Andras Laczo 2020. 01. 19.
 */

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNotes;

    public Notes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
