package gg.bayes.challenge.repository;

import gg.bayes.challenge.rest.model.HeroItems;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroItemsRepository extends JpaRepository<HeroItems, String> {

  List<HeroItems> findByHeroNameAndMatchId(String heroName,Long matchId);

}
