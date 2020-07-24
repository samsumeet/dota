package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hero_items")
public class HeroItems {
    @Id
    private String item;
    @JsonIgnore
    private String heroName;
    private Long timestamp;
    @JsonIgnore
    private Long matchId;
}
