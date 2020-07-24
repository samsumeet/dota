package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hero_kills")
public class HeroKills {

  @Id
  private String hero;
  private Integer kills = 0;
  @JsonIgnore
  private Long matchId;
}
