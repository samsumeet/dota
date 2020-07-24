package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroDamage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroDamageRepository extends JpaRepository<HeroDamage, String> {

  List<HeroDamage> findByHeroNameAndMatchId(String heroName, Long matchId);

}
