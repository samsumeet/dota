package gg.bayes.challenge.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "match_id")
public class MatchId {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
}
