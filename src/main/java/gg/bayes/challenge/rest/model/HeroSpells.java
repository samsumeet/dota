package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hero_spells")
public class HeroSpells {

  @Id
  private String spell;
  @JsonIgnore
  private String heroName;
  private Integer casts = 0;
  @JsonIgnore
  private Long matchId;
}
