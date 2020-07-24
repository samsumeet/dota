package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "hero_damage")
public class HeroDamage {

  @Id
  private String target;
  @JsonProperty("damage_instances")
  private Integer damageInstances = 0;
  @JsonProperty("total_damage")
  private Integer totalDamage = 0;
  @JsonIgnore
  private String heroName;
  @JsonIgnore
  private Long matchId;
}
