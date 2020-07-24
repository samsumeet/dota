package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroSpells;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroSpellsRepository extends JpaRepository<HeroSpells, String> {

  List<HeroSpells> findByHeroNameAndMatchId(String heroName,Long matchId);

}
